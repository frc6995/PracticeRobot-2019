package frc.robot.commands.climb.climbcg;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Retracts the Front Wheels
 */
public class ClimbRetractFrontC extends Command {
  public ClimbRetractFrontC() {
    requires(Robot.m_ClimbS);
  }

  @Override
  protected void initialize() {
  }

  // Retracts the front wheels
  @Override
  protected void execute() {
    Robot.m_ClimbS.retractFront();
  }

  // When retracted, end command
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
