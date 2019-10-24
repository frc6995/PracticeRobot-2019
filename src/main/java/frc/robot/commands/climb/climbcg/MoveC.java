package frc.robot.commands.climb.climbcg;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Move Block
 */
public class MoveC extends Command {

  // Variables
  public double speed;
  public double timeout;

  /**
   * Move forward at set speed
   * @param speed desired speed
   * @param timeout desired timeout
   */
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
    Robot.m_ClimbS.legWheels(speed, timeout);
  }

  // Until Timed Out, or a limit switch is pressed  
  @Override
  protected boolean isFinished() {
    return isTimedOut() || Robot.m_ClimbS.frontPistons() || Robot.m_ClimbS.rearPistons();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
