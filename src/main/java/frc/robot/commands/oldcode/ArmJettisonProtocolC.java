package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.cargo.CargoArmS.ArmLevel;

/**
 * jettison protocol for the arm
 * 
 * 1. Jettisons Cargo, keeps spinning wheels until ended
 * 2. set arm level home
 * 3. move to home
 */
public class ArmJettisonProtocolC extends Command {
  public ArmJettisonProtocolC() {
    requires(Robot.m_CargoArmS);
    requires(Robot.m_CargoClawS);
  }

  @Override
  protected void initialize() {
  }

  //jettisons, then moves home
  @Override
  protected void execute() {
    Robot.m_CargoClawS.cargoSpeed(0.7);
    Robot.m_CargoArmS.setNextArmLevel(ArmLevel.ARM_HOME);
    Robot.m_CargoArmS.runPid();
  }

  //when at home, end command
  @Override
  protected boolean isFinished() {
    return Robot.m_CargoArmS.isAtSetPoint();
  }

  //when ended, set motor speed to 0
  @Override
  protected void end() {
    Robot.m_CargoClawS.cargoSpeed(0.0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
