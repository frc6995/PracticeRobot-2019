package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class CargoShipC extends Command {
  public CargoShipC() {
    requires(Robot.m_CargoArmS);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (Robot.m_CargoArmS.getEncoderCount() >= RobotMap.ARM_HOME && Robot.m_CargoArmS.getEncoderCount() <= RobotMap.ARM_ROCKET) {
      Robot.m_CargoArmS.up();
    }
  }

  @Override
  protected boolean isFinished() {
    return Robot.m_CargoArmS.Ship();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
