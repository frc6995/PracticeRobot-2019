package frc.robot.commands.climb.climbcg.moveforwardcg;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//retracts wheels
public class MoveClimbRetractC extends Command {
  public MoveClimbRetractC() {
    requires(Robot.m_ClimbS);
  }

  @Override
  protected void initialize() {
  }

  //retracts wheels
  @Override
  protected void execute() {
    Robot.m_ClimbS.retract();
  }

  //done retracting wheels
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
