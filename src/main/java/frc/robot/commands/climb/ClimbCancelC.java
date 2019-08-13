package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * cancels climb mechanism, retracts front and back in tandem
 */
public class ClimbCancelC extends Command {
  public ClimbCancelC() {
    requires(Robot.m_ClimbS);
  }

  @Override
  protected void initialize() {
  }

  //retracts leg wheels in tandem
  @Override
  protected void execute() {
    Robot.m_ClimbS.retract();
  }

  //returns true when retract is completed
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
