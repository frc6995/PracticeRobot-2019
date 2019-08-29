package frc.robot.commands.arm.armcg;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *  Holds PID at Set Point Until Interrupted
 */
public class ArmHoldC extends Command {
  // Interruptable
  public ArmHoldC() {
    requires(Robot.m_ArmS);
    this.setInterruptible(true);
  }

  @Override
  protected void initialize() {
  }

  // Holds pid at desired set point
  @Override
  protected void execute() {
    Robot.m_ArmS.runPid();
  }

  // Keeps running runPid()
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
