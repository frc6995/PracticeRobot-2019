package frc.robot.commands.climbcg;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Retracts Rear Wheels
 */
public class RetractRearC extends Command {
  public RetractRearC() {
    requires(Robot.m_ClimbS);
  }

  // Interrupt MoveC when Middle Ultrasonic sensor above podium
  @Override
  protected void initialize() {
    Robot.m_ClimbS.centerIsOver();
  }

  // Retract Rear Wheels
  @Override
  protected void execute() {
    Robot.m_ClimbS.retractRear();
  }

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
