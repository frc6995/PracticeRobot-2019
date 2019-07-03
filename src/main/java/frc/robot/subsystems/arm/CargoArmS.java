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
  
  //encoder constants for levels 
  public int armHome = 0;
  public int armRocket = 500;
  public int armShip = 1000;


  public boolean Home() {
    if(getEncoderCount() == armHome) {
      return true;
    } else {
      return false;
    }
  }

  public boolean Rocket() {
    if (getEncoderCount() == armRocket) {
      return true;
    } else {
      return false;
    }
  }
  public boolean Ship() {
    if (getEncoderCount() == armShip) {
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

  }
  public void down() {

  }

  public void limitSwitchPressed() {
    if (armLowerLimitSwitch.get() == true) {
      if (getEncoderCount() != armHome) {
        up();
      }
    }

    if (armUpperLimitSwitch.get() == true) {
      if (getEncoderCount() != armShip) {
        down();
      }
    }
  }
}
