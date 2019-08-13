package frc.robot.commands.climb.climbcg;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * deploys the wheels
 */
public class ClimbDeployC extends Command {
  public ClimbDeployC() {
    requires(Robot.m_ClimbS);
  }

  @Override
  protected void initialize() {
  }

  //deploys the wheels
  @Override
  protected void execute() {
    Robot.m_ClimbS.deploy();
  }

  //returns true when wheels have deployed
  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}