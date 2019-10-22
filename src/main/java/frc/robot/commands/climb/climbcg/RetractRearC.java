package frc.robot.commands.climb.climbcg;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Retracts Rear Wheels
 */
public class RetractRearC extends Command {
  
  // Variables
  public double podium;

  /**
   * Retracts Front Wheels
   * @param podium how far away the podium is
   */
  public RetractRearC(double podium) {
    requires(Robot.m_ClimbS);
  }

  // Interrupt MoveC when Rear Ultrasonic sensor above podium
  @Override
  protected void initialize() {
    Robot.m_ClimbS.centerIsOver(podium);
  }

  // Retract Front Wheels
  @Override
  protected void execute() {
    Robot.m_ClimbS.retractRear();
  }

  // Until the pistons are fully retracted
  @Override
  protected boolean isFinished() {
    return Robot.m_ClimbS.rearPistons();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
