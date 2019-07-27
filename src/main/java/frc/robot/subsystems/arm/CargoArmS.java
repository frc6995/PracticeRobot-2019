package frc.robot.subsystems.arm;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class CargoArmS extends Subsystem {

  public WPI_TalonSRX armTalonA = null; // armTalonA should be the talon with the encoder
  public WPI_TalonSRX armTalonB = null;
  private DigitalInput armUpperLimitSwitch;
  private DigitalInput armLowerLimitSwitch;

  public int countWithinSetPoint = 0;
  private int setPointRange = RobotMap.ARM_SHIP;

  @Override
  public void initDefaultCommand() {
  }

  public CargoArmS() {
    armTalonA = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_ARM_A);
    armTalonB = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_ARM_B);

    armUpperLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_ARM_UPPER);
    armLowerLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_ARM_LOWER);
    
    armTalonA.configFactoryDefault(100);
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

    /*Limit switch configuration*/
    
    armUpperLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_ARM_UPPER);
    armLowerLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_ARM_LOWER);
  }
  public int getArmSetPointEncoderCount() {

    if (getEncoderCount() == RobotMap.ARM_HOME) {
      return RobotMap.ARM_HOME;
    } else if (getEncoderCount() == RobotMap.ARM_ROCKET){
      return RobotMap.ARM_ROCKET;
    } else if (getEncoderCount() == RobotMap.ARM_SHIP) {
      return RobotMap.ARM_SHIP;    
    } else {
      return 0;
    }

  }
  //boolean values that tell returns true if it is at the desired set point
  public boolean isHome() {
    if(getEncoderCount() == RobotMap.ARM_HOME) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isRocket() {
    if (getEncoderCount() == RobotMap.ARM_ROCKET) {
      return true;
    } else {
      return false;
    }
  }
  public boolean isShip() {
    if (getEncoderCount() == RobotMap.ARM_SHIP) {
      return true;
    } else {
      return false;
    }
  }
  
  public double getEncoderCount() {
    return (armTalonA.getSensorCollection().getQuadraturePosition());
  }

  public int getError() {
    return armTalonA.getClosedLoopError();
  }



  //functions for what limit switches do if pressed
  public void up() {
    armTalonA.set(ControlMode.Position, getArmSetPointEncoderCount());
    if (Math.abs(getError()) <= setPointRange) {
      countWithinSetPoint++;
    } else {
      limitSwitchPressed();
    }
  }

  public void down() {
    armTalonA.set(ControlMode.Position, getArmSetPointEncoderCount());
    if (Math.abs(getError()) <= setPointRange) {
      countWithinSetPoint--;
    } else {
      limitSwitchPressed();
    }
  }

  //tells the arm what to do if limit switches are pressed
  public void limitSwitchPressed() {
    if (armLowerLimitSwitch.get() == true) {
      if (getEncoderCount() != RobotMap.ARM_HOME) {
        up();
      }
    }

    if (armUpperLimitSwitch.get() == true) {
      if (getEncoderCount() != RobotMap.ARM_SHIP) {
        down();
      }
    }
  }
}
