package frc.robot.commands.arm.pid;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * moves the PID to desired set point set in SetLevelC
 */
public class MovePidC extends Command {
  public MovePidC() {
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
    return Robot.m_CargoArmS.isAtSetPoint();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
