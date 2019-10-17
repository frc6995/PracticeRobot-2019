package frc.robot.commands.climbcg;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Deploys the Wheels
 */
public class DeployC extends Command {
  public DeployC() {
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
