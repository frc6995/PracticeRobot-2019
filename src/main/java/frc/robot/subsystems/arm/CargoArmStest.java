package frc.robot.subsystems.arm;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class CargoArmS extends Subsystem {

  public enum ArmLevel {
    LEVEL_ZERO, LEVEL_CONTROL, LEVEL_ONE
  }

  public WPI_TalonSRX armTalonA = null;
  public WPI_TalonSRX armTalonB = null;

  private DigitalInput armUpperLimitSwitch;
  private DigitalInput armLowerLimitSwitch;

  public Encoder armPositionSensor;

  private ArmLevel currentArmLevel = ArmLevel.LEVEL_ONE;
  private ArmLevel nextArmLevel = ArmLevel.LEVEL_ONE;

  // Range in encoder counts where we consider ourselves "at" the set point
  private int setPointRange = 250;

  // Counts how many loops we have been within the Arm set point
  private int countWithinSetPoint = 0;

  // PID "constants"
  private boolean ArmPIDActive = true;
  // Proportional constant
  //  Without feedforward
    private double ArmKp = 0.65; //Up

  //with feedforward
  //private double ArmKp = 0.01;


  private double ArmDownKp = 0.05;  //Down
  // Integral constant
  private double ArmKi = 0.0009;
  // Derivative constant
  private double ArmKd = 0.0;
  // Feedforward = power needed to hold the Arm in a constant spot
  private double ArmKf = 0;

  private int currentLimit = 20;

  // The talon PID slot we are using, this should not change
public static final int Arm_PID_SLOT = 0;



  @Override
  public void initDefaultCommand() {
  }

  public CargoArmS() {

    armTalonA = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_ARM_A);
    armTalonB = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_ARM_B);
    armUpperLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_ARM_UPPER);
    armLowerLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_ARM_LOWER);
    armPositionSensor = new Encoder(RobotMap.DIO_LIMIT_ARM_UPPER, RobotMap.DIO_LIMIT_ARM_LOWER, false, Encoder.EncodingType.k4X);

    armPositionSensor.setMaxPeriod(.1);
    armPositionSensor.setMinRate(10);
    armPositionSensor.setDistancePerPulse(5);
    armPositionSensor.setReverseDirection(true);
    armPositionSensor.setSamplesToAverage(7);

    armTalonB.follow(armTalonA);

    
    // Doesn't apply to voltage control
    armTalonA.configForwardSoftLimitThreshold(8000);  //Stops the Arm at the top
    armTalonA.configForwardSoftLimitEnable(false);
    
  }

  public boolean armUpperLimitSwitchPressed() {
    // Returns true if the sensor is pressed, false if it is not
    return armUpperLimitSwitch.get();
  }

  public boolean armLowerLimitSwitch() {
    return armLowerLimitSwitch.get();
  }

  public void resetEncoder() {
    armTalonA.getSensorCollection().setQuadraturePosition(3, 500);
    armTalonA.setSelectedSensorPosition(0);
}


public void setArmPower(double power) {
  // Positive is up, negative is down
  armTalonA.set(ControlMode.PercentOutput, power);
}

//Sets the max power the PID can apply
public void setMaxPIDPower(double power){
  armTalonA.configClosedLoopPeakOutput(Arm_PID_SLOT, power);
}

//For using a less agressive Kp on the way down
public void useUpKp(){
  armTalonA.config_kP(Arm_PID_SLOT, ArmKp);
}
public void useDownKp(){
  armTalonA.config_kP(Arm_PID_SLOT, ArmDownKp);
}

public double getArmEncoderCount() {
  // Positive is up, negative is down
  return (armTalonA.getSensorCollection().getQuadraturePosition());
}

public int getError() {
  return armTalonA.getClosedLoopError();
}

public void enablePID() {
  ArmPIDActive = true;
}

public void disablePID() {
  ArmPIDActive = false;
  countWithinSetPoint = 0;
  // Running a "set power" command will stop any active position control
  setArmPower(0);
  armTalonA.neutralOutput();
}

public void runPID() {
  ArmPIDActive = true;

  // Tuning/testing outputs
  //SmartDashboard.putNumber("Encoder pos", ArmTalonA.getSensorCollection().getQuadraturePosition());
  //SmartDashboard.putNumber("Error", getError());
  //SmartDashboard.putBoolean("IsAtSetPoint", isAtSetPoint());
  //SmartDashboard.putNumber("Power", ArmTalonA.getMotorOutputPercent());
  //SmartDashboard.putNumber("Talon Set point", ArmTalonA.getClosedLoopTarget());
  //SmartDashboard.putNumber("Code Set point", getArmSetPointEncoderCount());
  //SmartDashboard.putString("Next Arm level", ArmLevelToString(nextArmLevel));
  //SmartDashboard.putNumber("Integral sum", ArmTalonA.getIntegralAccumulator());
  //SmartDashboard.putNumber("Derivative", ArmTalonA.getErrorDerivative());
  //SmartDashboard.putString("Arm level",ArmLevelToString(getNextArmLevel()));

  armTalonA.set(ControlMode.Position, getArmSetPointEncoderCount());

  // If we are within the set point range add 1 to countWithinSetPoint, else set to 0
  if (Math.abs(getError()) < setPointRange) {
    countWithinSetPoint++;
  } else {
    countWithinSetPoint = 0;
  }
}

public void displayStatus() {
  String ArmStatus = "null";

  if (isAtSetPoint() && ArmPIDActive) {
    // Example: "Holding at level 3."
    ArmStatus = "Holding at level: " + ArmLevelToString(getCurrentArmLevel()) + ".";
  } else if (!isAtSetPoint() && ArmPIDActive) {
    // Example: "Moving down to level 0."
    ArmStatus = "Moving ";
    if (armTalonA.getMotorOutputPercent() > 0) {
      ArmStatus += "up ";
    } else {
      ArmStatus += "down ";
    }
    ArmStatus += "to level " + ArmLevelToString(getNextArmLevel()) + ".";
  } else if (!ArmPIDActive) {
    ArmStatus = "Idle";
  }
  else {
    ArmStatus = "REKT";
  }
  SmartDashboard.putString("Arm status", ArmStatus);
}

public boolean isAtSetPoint() {
  //If we have been within our set point range for the given time, return true
  if (countWithinSetPoint > 15) {
    currentArmLevel = nextArmLevel;
    countWithinSetPoint = 0;
    return true;
  } else {
    return false;
  }
}

public void setCurrentArmLevel(ArmLevel currentLevel) {
  currentArmLevel = currentLevel;
}

public ArmLevel getCurrentArmLevel() {
  return currentArmLevel;
}

public int getArmSetPointEncoderCount(){
  //SmartDashboard.putBoolean("Below Cushion", (getArmEncoderCount() < RobotMap.Arm_LEVEL_CUSHION));
  if(getNextArmLevel() == ArmLevel.LEVEL_ONE){
    if (getArmEncoderCount() < RobotMap.Arm_LEVEL_CUSHION) {
      return RobotMap.Arm_LEVEL_ONE;
    }
    else
      return RobotMap.Arm_LEVEL_CUSHION; 
  }
  else if (getNextArmLevel() == ArmLevel.LEVEL_CARGO_SHIP) {
    return RobotMap.LEVEL_CARGO_SHIP;
  }
  else if (getNextArmLevel() == ArmLevel.LEVEL_TWO) {
    return RobotMap.Arm_LEVEL_TWO;
  }
  else if (getNextArmLevel() == ArmLevel.LEVEL_THREE) {
    return RobotMap.Arm_LEVEL_THREE;
  }
  else if(getNextArmLevel() == ArmLevel.LEVEL_CARGO_INTAKE){
    return RobotMap.Arm_LEVEL_CARGO_INTAKE;
  }
  else if(getNextArmLevel() == ArmLevel.LEVEL_BOTTOM) {
    return 0;
  } 
  else {
    return 0;
  }
}

public void setNextArmLevel(ArmLevel nextLevel) {
  nextArmLevel = nextLevel;
}

public ArmLevel getNextArmLevel() {
  return nextArmLevel;
}

public boolean lowerLimitSwitchPressed() {
  // Returns true if the sensor is pressed, false if it is not
  return ArmLowerLimitSwitch.get();
}

public void resetEncoder() {
  ArmTalonA.getSensorCollection().setQuadraturePosition(3, 500);
  ArmTalonA.setSelectedSensorPosition(0);
}

public String ArmLevelToString(ArmLevel level) {
  switch(level) {
    case LEVEL_ONE:
      return "1";
    case LEVEL_CUSHION:
      return "Cushion";
    case LEVEL_TWO:
      return "2";
    case LEVEL_THREE:
      return "3";
    case LEVEL_CARGO_INTAKE:
      return "4";
    case LEVEL_BOTTOM:
      return "6";
    case LEVEL_CARGO_SHIP:
      return "Cargo Ship";
    default:
      return "Unknown. Illegal ArmLevel type.";
  }
}
}
}
