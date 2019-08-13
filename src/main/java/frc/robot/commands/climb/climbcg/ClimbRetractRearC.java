package frc.robot.commands.climb.climbcg;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * retracts the Rear wheels
 */
public class ClimbRetractRearC extends Command {
  public ClimbRetractRearC() {
    requires(Robot.m_ClimbS);
  }

  @Override
  protected void initialize() {
  }

  //Retracts the rear wheels
  @Override
  protected void execute() {
    Robot.m_ClimbS.retractRear();
  }

  //when retracted, end command
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
