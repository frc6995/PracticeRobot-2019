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
    if (Robot.m_CargoArmS.getEncoderCount() >= RobotMap.ARM_HOME && Robot.m_CargoArmS.getEncoderCount() <= RobotMap.ARM_SHIP) {
      Robot.m_CargoArmS.down();
    }
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
