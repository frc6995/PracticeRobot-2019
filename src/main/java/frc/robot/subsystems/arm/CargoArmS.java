package frc.robot.subsystems.arm;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class CargoArmS extends Subsystem {

  public WPI_TalonSRX armTalonA = null;
  public WPI_TalonSRX armTalonB = null;// armTalonB should be the talon without encoder
  private DigitalInput armUpperLimitSwitch;
  private DigitalInput armLowerLimitSwitch;


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

  }

  public double getEncoderCount() {
    return (armTalonA.getSensorCollection().getQuadraturePosition());
  }

  public void up() {

    /* old code
    ladderPIDActive = true;

    armTalonA.set(ControlMode.Position, getLadderSetPointEncoderCount());

    // If we are within the set point range add 1 to countWithinSetPoint, else set to 0
    if (Math.abs(getError()) < setPointRange) {
      countWithinSetPoint++;
    } else {
      countWithinSetPoint = 0;
    
    }
    */
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
