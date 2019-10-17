package frc.robot.commands.climbcg;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Move Block
 */
public class MoveC extends Command {

  // Sets Desired speed
  public double speed;

  // Move forward at set speed, and continues to do so until reaching desired timeout, or interrupted
  public MoveC(double speed, double timeout) {
    requires(Robot.m_ClimbS);
    this.setInterruptible(true);
    this.setTimeout(timeout);
  }

  @Override
  protected void initialize() {
  }

  // Moves for speed
  @Override
  protected void execute() {
    Robot.m_ClimbS.legWheels(speed);
  }

  // Until Timed Out  
  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
