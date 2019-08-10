package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * retracts front wheels when limit switch is pressed
 */
public class RetractFrontC extends Command {
  public RetractFrontC() {
    requires(Robot.m_ClimbS);
  }

  @Override
  protected void initialize() {
    Robot.m_ClimbS.frontLimit();
  }

  @Override
  protected void execute() {
    Robot.m_ClimbS.retractFront();
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
