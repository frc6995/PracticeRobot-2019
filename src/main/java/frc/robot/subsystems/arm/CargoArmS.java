package frc.robot.subsystems.arm;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
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
    
    armTalonA.configFactoryDefault();
    armTalonB.configFactoryDefault();

    armTalonB.follow(armTalonA);
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
