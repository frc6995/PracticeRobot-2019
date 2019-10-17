package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Retracts Wheels
 */
public class ClimbCancelC extends Command {
  public ClimbCancelC() {
    requires(Robot.m_ClimbS);
  }

  @Override
  protected void initialize() {
  }

  // Retracts wheels
  @Override
  protected void execute() {
    Robot.m_ClimbS.retract();
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
