package frc.robot.subsystems.cargo;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class CargoClawS extends Subsystem {

  public static Spark cargoIntakeMotor;
  private static DigitalInput cargoLimit;

  public CargoClawS() {
    cargoIntakeMotor = new Spark(RobotMap.PWM_ID_SPARK_HAND);
    cargoLimit = new DigitalInput(RobotMap.DIO_LIMIT_HAND);
  }

  @Override
  public void initDefaultCommand() {
  }

  public void cargoSpeed(double speed) {
    cargoIntakeMotor.set(speed);
  }

  public boolean getCargoLimit() {
    return cargoLimit.get();
  }
}
