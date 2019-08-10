package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * intakes the cargo and hits limit switch,
 * providing runPID to execute calculations regarding wether or not the arm is loaded 
 */
public class ClawIntakeC extends Command {

  private double intakeSpeed = -0.7; //negative values intake?

  public ClawIntakeC() {
    requires(Robot.m_CargoClawS);
  }

  @Override
  protected void initialize() {
    this.setTimeout(10); //whatever value
  }

  @Override
  protected void execute() {
    Robot.m_CargoClawS.cargoSpeed(intakeSpeed);
  }

  @Override
  protected boolean isFinished() {
    return Robot.m_CargoClawS.getCargoLimit() || isTimedOut();
  }

  @Override
  protected void end() {
    Robot.m_CargoClawS.cargoSpeed(0.0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}