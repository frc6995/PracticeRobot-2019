package frc.robot.commands.climb.climbcg;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Retracts Front Wheels
 */
public class RetractFrontC extends Command {

  // Variables
  public double podium;

  /**
   * Retracts Front Wheels
   * @param podium how far away the podium is
   */
  public RetractFrontC(double podium) {
    requires(Robot.m_ClimbS);
  }

  // Interrupt MoveC when Front Ultrasonic sensor above podium
  @Override
  protected void initialize() {
    Robot.m_ClimbS.frontIsOver(podium);
  }

  // Retract Front Wheels
  @Override
  protected void execute() {
    Robot.m_ClimbS.retractFront();
  }

  // Until the pistons are fully retracted
  @Override
  protected boolean isFinished() {
    return Robot.m_ClimbS.frontPistons();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
