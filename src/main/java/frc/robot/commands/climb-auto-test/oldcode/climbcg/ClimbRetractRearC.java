package frc.robot.commands.climb.climbcg;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Retracts the Rear Wheels
 */
public class ClimbRetractRearC extends Command {
  public ClimbRetractRearC() {
    requires(Robot.m_ClimbS);
  }

  @Override
  protected void initialize() {
  }

  // Retracts the rear wheels
  @Override
  protected void execute() {
    Robot.m_ClimbS.retractRear();
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
