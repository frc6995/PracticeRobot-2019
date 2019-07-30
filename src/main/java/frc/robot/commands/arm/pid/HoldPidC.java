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

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_CargoArmS.runPid();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
