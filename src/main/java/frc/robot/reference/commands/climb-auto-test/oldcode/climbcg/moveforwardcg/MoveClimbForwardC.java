package frc.robot.commands.climb.climbcg.moveforwardcg;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Moves Forward With Timout
 */
public class MoveClimbForwardC extends Command {
  
  // Arguments for how long Patrick moves forwards
  public MoveClimbForwardC(int time) {
    requires(Robot.m_ClimbS);
    this.setTimeout(time);
  }

  @Override
  protected void initialize() {
  }

  // Runs wheels
  @Override
  protected void execute() {
    Robot.m_ClimbS.legWheels(0.7);
  }

  // Until timed out
  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  // Stops wheels
  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
