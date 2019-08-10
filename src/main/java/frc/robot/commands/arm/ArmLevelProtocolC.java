package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.cargo.CargoArmS.ArmLevel;

/**
 * Climb Protocol
 * 
 * 1. sets desired arm level
 * 2. moves to desired arm level
 * 3. holds position with same Pid until interrupted
 */
public class ArmLevelProtocolC extends Command {

  //tells when turned on this is Home position
  ArmLevel nextArmLevel = ArmLevel.ARM_HOME;

  public ArmLevelProtocolC(ArmLevel level) {
    requires(Robot.m_CargoArmS);
    //sets desired level
    nextArmLevel = level;
    //this is an interruptable command
    this.setInterruptible(true);
  }

  @Override
  protected void initialize() {
  }

  //runs Pid to go to desired set point
  @Override
  protected void execute() {
    Robot.m_CargoArmS.setNextArmLevel(nextArmLevel);
    Robot.m_CargoArmS.runPid();
  }

  //we want it to keep running runPid()
  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
