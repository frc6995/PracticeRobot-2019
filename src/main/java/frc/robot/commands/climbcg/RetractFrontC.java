package frc.robot.commands.climbcg;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Retracts Front Wheels
 */
public class RetractFrontC extends Command {
  public RetractFrontC() {
    requires(Robot.m_ClimbS);
  }

  // Interrupt MoveC when Front Ultrasonic sensor above podium
  @Override
  protected void initialize() {
    Robot.m_ClimbS.frontIsOver();
  }

  // Retract Front Wheels
  @Override
  protected void execute() {
    Robot.m_ClimbS.retractFront();
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
