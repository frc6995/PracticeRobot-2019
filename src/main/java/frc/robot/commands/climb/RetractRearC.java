package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * retracts the rear wheels when rear limit switch is pressed
 */
public class RetractRearC extends Command {
  public RetractRearC() {
    requires(Robot.m_ClimbS);
  }

  @Override
  protected void initialize() {
    Robot.m_ClimbS.rearLimit();
  }

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
  }
}
