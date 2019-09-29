package frc.robot.commands.climb.climbcg;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Move Forward Back
 */
public class ClimbMoveForwardBC extends Command {

  public ClimbMoveForwardBC() {
    requires(Robot.m_ClimbS);
  }

  // Start command when front cylinders are fully retracted
  @Override
  protected void initialize() {
    Robot.m_ClimbS.SolenoidPosF = true;
  }

  // Speeds up
  @Override
  protected void execute() {
    Robot.m_ClimbS.legWheels(0.7);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.m_ClimbS.backRetract;
  }

  // Stops wheels, and then retracts Rear Cylinders
  @Override
  protected void end() {
    Robot.m_ClimbS.legWheels(0.0);
    Robot.m_ClimbS.retractFront();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
