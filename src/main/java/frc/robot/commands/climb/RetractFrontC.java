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

  //if front limit is pressed...
  @Override
  protected void initialize() {
    Robot.m_ClimbS.frontLimit();
  }

  //retract the front
  @Override
  protected void execute() {
    Robot.m_ClimbS.retractFront();
  }

  //retracts wheels once
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
