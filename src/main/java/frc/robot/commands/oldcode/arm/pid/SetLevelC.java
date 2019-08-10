package frc.robot.commands.arm.pid;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.cargo.CargoArmS.ArmLevel;

/**
 * sets the level of the arm where the PID should go, does not run the PID
 */
public class SetLevelC extends Command {
  //tells the PID when turned on that this is the home Position
  ArmLevel nextArmLevel = ArmLevel.ARM_HOME;

  public SetLevelC(ArmLevel level) {
    nextArmLevel = level;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_CargoArmS.setNextArmLevel(nextArmLevel);
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
    Robot.m_CargoArmS.setNextArmLevel(nextArmLevel);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
