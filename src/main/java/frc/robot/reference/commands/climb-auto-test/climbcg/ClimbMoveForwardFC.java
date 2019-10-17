package frc.robot.commands.climb.climbcg;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Move Forward Front
 */
public class ClimbMoveForwardFC extends Command {

  // Interruptable
  public ClimbMoveForwardFC() {
    requires(Robot.m_ClimbS);
    this.setInterruptible(true);
  }

  @Override
  protected void initialize() {
  }

  // Leg wheels move forward
  @Override
  protected void execute() {
    Robot.m_ClimbS.legWheels(0.7);
  }

  // When ultrasonic sensor sees it is on the podium
  @Override
  protected boolean isFinished() {
    return Robot.m_ClimbS.frontRetract;
  }

  // Retract front wheels, and slows down
  @Override
  protected void end() {
      Robot.m_ClimbS.retractFront();
      Robot.m_ClimbS.legWheels(0.4);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
