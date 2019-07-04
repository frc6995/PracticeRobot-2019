package frc.robot.subsystems.arm;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class CargoArmS extends Subsystem {

  public WPI_TalonSRX armTalonA = null;
  public WPI_TalonSRX armTalonB = null;// armTalonB should be the talon without encoder
  private DigitalInput armUpperLimitSwitch;
  private DigitalInput armLowerLimitSwitch;

  public int countWithinSetPoint = 0;
  private int setPointRange = RobotMap.ARM_SHIP;


  public int getArmSetPointEncoderCount() {

      return 0;

  }


  public boolean Home() {
    if(getEncoderCount() == RobotMap.ARM_HOME) {
      return true;
    } else {
      return false;
    }
  }

  public boolean Rocket() {
    if (getEncoderCount() == RobotMap.ARM_ROCKET) {
      return true;
    } else {
      return false;
    }
  }
  public boolean Ship() {
    if (getEncoderCount() == RobotMap.ARM_SHIP) {
      return true;
    } else {
      return false;
    }
  }
  

  @Override
  public void initDefaultCommand() {
  }

  public CargoArmS() {

    armTalonA = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_ARM_A);
    armTalonB = new WPI_TalonSRX(RobotMap.CAN_ID_TALON_ARM_B);

    armUpperLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_ARM_UPPER);
    armLowerLimitSwitch = new DigitalInput(RobotMap.DIO_LIMIT_ARM_LOWER);

    armTalonB.follow(armTalonA);

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
