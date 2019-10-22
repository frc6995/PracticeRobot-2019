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
    Robot.m_ClimbS.deploy();
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
