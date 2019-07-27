package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class CargoHomeC extends Command {
  public CargoHomeC() {
    requires(Robot.m_CargoArmS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
      Robot.m_CargoArmS.runPID();
  }

  @Override
  protected boolean isFinished() {
    return Robot.m_CargoArmS.isHome();
  }

  @Override
  protected void end() {
    end();
  }

  @Override
  protected void interrupted() {
  }
}
