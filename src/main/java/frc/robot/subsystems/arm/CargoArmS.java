package frc.robot.subsystems.arm;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
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

}
