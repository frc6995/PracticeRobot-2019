package frc.robot.subsystems.arm;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class CargoIntakeS extends Subsystem {

  public Spark cargoIntakeMotor;
  private static DigitalInput cargoLimit;

  public CargoIntakeS() {

    cargoIntakeMotor = new Spark(RobotMap.PWM_ID_SPARK_CARGO);
    cargoLimit = new DigitalInput(RobotMap.PWM_ID_LIMIT_CARGO);

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
