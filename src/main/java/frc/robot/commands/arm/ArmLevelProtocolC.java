package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.ArmS.ArmLevel;

/**
 * Climb Protocol
 */
public class ArmLevelProtocolC extends Command {

  // Home is the default arm position
  ArmLevel nextArmLevel = ArmLevel.ARM_HOME;

  // Gets desired arm level, interupptable, has argument 'level'
  public ArmLevelProtocolC(ArmLevel level) {
    requires(Robot.m_ArmS);
    nextArmLevel = level;
    this.setInterruptible(true);
  }

  // Sets desired arm level
  @Override
  protected void initialize() {
    Robot.m_ArmS.setNextArmLevel(nextArmLevel);
  }

  // Runs PID to go to desired set point
  @Override
  protected void execute() {
    Robot.m_ArmS.runPid();
  }

  // Keep running runPid() to hold
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
