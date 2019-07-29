package frc.robot.subsystems.arm;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class CargoHandS extends Subsystem {

  public static Spark cargoIntakeMotor;
  private static DigitalInput cargoLimit;

  public CargoHandS() {
    cargoIntakeMotor = new Spark(RobotMap.PWM_ID_SPARK_HAND);
    cargoLimit = new DigitalInput(RobotMap.DIO_LIMIT_HAND);
  }

  @Override
  public void initDefaultCommand() {
  }

  public void cargoSpeed(double speed) {
    cargoIntakeMotor.set(speed);
  }

  public static boolean getCargoLimit() {
    return cargoLimit.get();
  }
}
