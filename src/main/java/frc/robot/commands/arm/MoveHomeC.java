package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.cargo.CargoArmS.ArmLevel;

/**
 * sets the PID to home and moves to home
 */
public class MoveHomeC extends Command {
  public MoveHomeC() {
    requires(Robot.m_CargoArmS);
  }

  //sets arm level to home
  @Override
  protected void initialize() {
    Robot.m_CargoArmS.setNextArmLevel(ArmLevel.ARM_HOME);
  }

  //runs the pid to home
  @Override
  protected void execute() {
    Robot.m_CargoArmS.runPid();
  }

  //if at set point, end command
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
