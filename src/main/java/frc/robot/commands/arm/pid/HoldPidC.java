package frc.robot.commands.arm.pid;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * holds PID at set point until interrupted
 */
public class HoldPidC extends Command {
  public HoldPidC() {
    requires(Robot.m_CargoArmS);
    this.setInterruptible(true);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_CargoArmS.runPid();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
