/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

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
    //inverses the output of the limit swich, when it is pressed send false and when not pressed send true.
    return !cargoLimit.get();
  }
}
