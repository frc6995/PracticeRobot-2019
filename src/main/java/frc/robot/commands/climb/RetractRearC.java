package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Retracts Rear Wheels
 */
public class RetractRearC extends Command {
  public RetractRearC() {
    requires(Robot.m_ClimbS);
  }

  @Override
  protected void initialize() {
  }

  // Retracts rear wheels
  @Override
  protected void execute() {
    Robot.m_ClimbS.retractRear();
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
