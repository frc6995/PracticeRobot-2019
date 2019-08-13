package frc.robot.commands.climb.climbcg;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * move forward for time
 */
public class MoveForwardTimoutC extends Command {
  public MoveForwardTimoutC() {
    requires(Robot.m_ClimbS);
    //timeout
    this.setTimeout(2);
  }

  @Override
  protected void initialize() {
  }

  //move wheels
  @Override
  protected void execute() {
    Robot.m_ClimbS.legWheels(0.7);
  }

  //moves forward for timeout
  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  @Override
  protected void end() {
    Robot.m_ClimbS.legWheels(0.0);
  }

  @Override
  protected void interrupted() {
  }
}
