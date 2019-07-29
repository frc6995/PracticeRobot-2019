package frc.robot.subsystems.arm;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class CargoArmS extends Subsystem {

  public enum ArmLevel {
    ARM_HOME, ARM_ROCKET, ARM_SHIP, ARM_REVERSE_ROCKET, ARM_REVERSE_SHIP
  }

  public WPI_TalonSRX armTalonA = null; // armTalonA should be the talon with the encoder
  public WPI_TalonSRX armTalonB = null;
  private DigitalInput armUpperLimitSwitch;
  private DigitalInput armLowerLimitSwitch;

  //private ArmLevel currentArmLevel = ArmLevel.ARM_HOME;
  private ArmLevel nextArmLevel = ArmLevel.ARM_HOME;
   // Range in encoder counts where we consider ourselves "at" the set point
   private int setPointRange = 250;

   //laymans terms's: amount of time it stays within the set points 

   // Counts how many loops we have been within the ladder set point
   private int countWithinSetPoint = 0;
   private final int setPointLoops = 0;
   

  @Override
  public void initDefaultCommand() {
  }

  public CargoArmS() {
    armTalonA = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_ARM_A);
    armTalonB = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_ARM_B);

    armUpperLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_ARM_UPPER);
    armLowerLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_ARM_LOWER);
    
    armTalonA.configFactoryDefault(100);//in ms
    armTalonB.configFactoryDefault(100);

    armTalonB.follow(armTalonA);

    armTalonA.setInverted(RobotMap.MOTOR_A_INVERT);
    armTalonB.setInverted(RobotMap.MOTOR_B_INVERT);

    armTalonA.setNeutralMode(RobotMap.MOTOR_A_NEUTRAL_MODE);
    armTalonB.setNeutralMode(RobotMap.MOTOR_B_NEUTRAL_MODE);

    armTalonA.enableCurrentLimit(RobotMap.CURRENT_LIMIT_ENABLED);
    armTalonA.configPeakCurrentLimit(RobotMap.CURRENT_PEAK_LIMIT_VALUE);
    armTalonA.configPeakCurrentDuration(RobotMap.CURRENT_PEAK_LIMIT_DURATION);
    armTalonA.configContinuousCurrentLimit(RobotMap.CURRENT_LIMIT_VALUE);

    armTalonA.configSelectedFeedbackSensor(RobotMap.FEEDBACK_DEVICE);
    armTalonA.setSensorPhase(RobotMap.FEEDBACK_DEVICE_INVERT);

    armTalonA.configForwardSoftLimitThreshold(RobotMap.LIMIT_SOFT_FORWARD, RobotMap.LIMIT_SOFT_TIMEOUT);
    armTalonA.configReverseSoftLimitThreshold(RobotMap.LIMIT_SOFT_REVERSE, RobotMap.LIMIT_SOFT_TIMEOUT);
    armTalonA.configForwardSoftLimitEnable(RobotMap.LIMIT_SOFT_ENABLED, RobotMap.LIMIT_SOFT_TIMEOUT);
    armTalonA.configReverseSoftLimitEnable(RobotMap.LIMIT_SOFT_ENABLED, RobotMap.LIMIT_SOFT_TIMEOUT);

    armTalonA.selectProfileSlot(RobotMap.LADDER_PID_SLOT, 0);
    armTalonA.configAllowableClosedloopError(RobotMap.LADDER_PID_SLOT, 0);

    armTalonA.config_kP(RobotMap.LADDER_PID_SLOT, RobotMap.kP);
    armTalonA.config_kI(RobotMap.LADDER_PID_SLOT, RobotMap.kI);
    armTalonA.config_kD(RobotMap.LADDER_PID_SLOT, RobotMap.kD);
    armTalonA.config_kF(RobotMap.LADDER_PID_SLOT, (RobotMap.kF * 1023) / RobotMap.ENCODER_DUTY_CYCLE);

    armTalonA.config_IntegralZone(RobotMap.LADDER_PID_SLOT, RobotMap.INTEGRAL_ZONE);

    armTalonA.configClosedLoopPeakOutput(RobotMap.LADDER_PID_SLOT, RobotMap.PEAK_OUTPUT);
    armTalonA.configClosedloopRamp(RobotMap.RAMP_TIME);

    armTalonA.set(ControlMode.PercentOutput, 0);
  }

  //PID encoder functions

  //tells the PID what encoder position we want to go to
  public int getArmSetPointEncoderCount() {
    if (nextArmLevel == ArmLevel.ARM_HOME) {
      return RobotMap.ARM_HOME;
    } else if (nextArmLevel == ArmLevel.ARM_ROCKET){
      return RobotMap.ARM_ROCKET;
    } else if (nextArmLevel == ArmLevel.ARM_SHIP) {
      return RobotMap.ARM_SHIP;
    } else if (nextArmLevel == ArmLevel.ARM_REVERSE_ROCKET) {
      return RobotMap.ARM_REVERSE_ROCKET;
    } else if (nextArmLevel == ArmLevel.ARM_REVERSE_SHIP) {
      return RobotMap.ARM_REVERSE_SHIP;   
    } else {
      return 0;
    }
  }

  //set arm level outside of class
  public void setNextArmLevel(ArmLevel nextLevel) {
    nextArmLevel = nextLevel;
  }

  //gets encoder current position
  public double getCurrentEncoderCount() {
    return (armTalonA.getSensorCollection().getQuadraturePosition());
  }

  public int getError() {
    return armTalonA.getClosedLoopError();
  }

  //PID container calculates kF_a
  public void runPID() {
    //arbitrary feed forward
    double  kF_a = 0.0;

    if(CargoHandS.getCargoLimit() == true){
      kF_a = RobotMap.kF_B * Math.cos(Math.toRadians((getCurrentEncoderCount() - RobotMap.ENCODER_POS_HORIZONTAL) / RobotMap.ENCODER_TICKS_PER_DEG));
    }else if (CargoHandS.getCargoLimit() == false){
      kF_a = RobotMap.kF_nB * Math.cos(Math.toRadians((getCurrentEncoderCount() - RobotMap.ENCODER_POS_HORIZONTAL) / RobotMap.ENCODER_TICKS_PER_DEG));
    }

    armTalonA.set(ControlMode.Position, getArmSetPointEncoderCount(), DemandType.ArbitraryFeedForward, kF_a);

    // If we are within the set point range add 1 to countWithinSetPoint, else set to 0
    if (Math.abs(getError()) < setPointRange) {
      //counts the loops
      countWithinSetPoint++;
    } else {
      //stops counting loops when outside set point range
      countWithinSetPoint = 0;
    }
  }

  public boolean isAtSetPoint() {
    //If we have been within our set point range for the given time, return true
    if (countWithinSetPoint > setPointLoops ) {
      countWithinSetPoint = 0;
      return true;
    } else {
      return false;
    }
  }

  //returns true if the limit switch is pressed
  public boolean lowerlimitSwitchPressed() {
    return armLowerLimitSwitch.get();
  }
  public boolean armUpperLimitSwitch() {
    return armUpperLimitSwitch.get();
  }
}