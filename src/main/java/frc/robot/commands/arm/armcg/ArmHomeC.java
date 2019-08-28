package frc.robot.commands.arm.armcg;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.ArmS.ArmLevel;

/**
 * sets the PID to home and moves to home
 */
public class ArmHomeC extends Command {
  public ArmHomeC() {
    requires(Robot.m_ArmS);
  }

  //sets arm level to home
  @Override
  protected void initialize() {
    Robot.m_ArmS.setNextArmLevel(ArmLevel.ARM_HOME);
  }

  //runs the pid to home
  @Override
  protected void execute() {
    Robot.m_ArmS.runPid();
  }

  //if at set point, end command
  @Override
  protected boolean isFinished() {
    return Robot.m_ArmS.isAtSetPoint();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
