package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Retracts Front Wheels
 */
public class RetractFrontC extends Command {
  public RetractFrontC() {
    requires(Robot.m_ClimbS);
  }

  @Override
  protected void initialize() {
  }

  // Retracts front wheels
  @Override
  protected void execute() {
    Robot.m_ClimbS.retractFront();
  }

  // Only runs once
  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
