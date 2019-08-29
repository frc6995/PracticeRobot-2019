package frc.robot.commands.climb.climbcg.moveforwardcg;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Drives Backwards, Stops, Then Retracts Wheels
 */
public class MoveClimbBackwardC extends Command {

  // How long Patrick moves backwards
  public MoveClimbBackwardC() {
    requires(Robot.m_ClimbS);
    this.setTimeout(1);
  }

  @Override
  protected void initialize() {
  }

  // Moves backwards
  @Override
  protected void execute() {
    Robot.m_ClimbS.legWheels(-0.7);
  }

  // Until timed out
  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  // Stops wheels
  @Override
  protected void end() {
    Robot.m_ClimbS.legWheels(0.0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
