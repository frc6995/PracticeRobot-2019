package frc.robot.commands.arm.armcg;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * holds PID at set point until interrupted
 */
public class ArmHoldC extends Command {
  public ArmHoldC() {
    requires(Robot.m_CargoArmS);
    //interruptable
    this.setInterruptible(true);
  }

  @Override
  protected void initialize() {
  }

  //holds pid at desired set point
  @Override
  protected void execute() {
    Robot.m_CargoArmS.runPid();
  }

  //forever, until interrupted
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
