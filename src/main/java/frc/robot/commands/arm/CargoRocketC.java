package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class CargoRocketC extends Command {
  public CargoRocketC() {
    requires(Robot.m_CargoArmS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (Robot.m_CargoArmS.getEncoderCount() >= RobotMap.ARM_HOME && Robot.m_CargoArmS.getEncoderCount() <= RobotMap.ARM_ROCKET) {
      Robot.m_CargoArmS.up();
    } else {
      Robot.m_CargoArmS.down();
    }
  }

  @Override
  protected boolean isFinished() {
    return Robot.m_CargoArmS.Rocket();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
