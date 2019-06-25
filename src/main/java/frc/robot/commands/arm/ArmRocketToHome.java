package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmRocketToHome extends Command {
  public ArmRocketToHome() {
    requires(Robot.m_CargoArmS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_CargoArmS.downPID();
  }

  @Override
  protected boolean isFinished() {
    return Robot.m_CargoArmS.armHome();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
