package frc.robot.commands.climb.climbcg;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Deploys the Wheels
 */
public class DeployC extends Command {

  // Variables
  public double altitude;

  /**
   * Deploys Wheels
   * @param altitude Desired height
   */
  public DeployC(double altitude) {
    requires(Robot.m_ClimbS);
  }

  @Override
  protected void initialize() {
  }

  // Deploys Wheels
  @Override
  protected void execute() {
    if (Robot.m_ClimbS.getDistanceFront() < Robot.m_ClimbS.getDistanceMiddle()) {
      Robot.m_ClimbS.deployFront();
      Robot.m_ClimbS.stopRear();
    } if (Robot.m_ClimbS.getDistanceMiddle() < Robot.m_ClimbS.getDistanceFront()) {
      Robot.m_ClimbS.deployRear();
      Robot.m_ClimbS.stopFront();
    } else {
      Robot.m_ClimbS.deployFront();
      Robot.m_ClimbS.deployRear();
    } 
  }

  // Until reached desired height
  @Override
  protected boolean isFinished() {
    return Robot.m_ClimbS.height(altitude);
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
