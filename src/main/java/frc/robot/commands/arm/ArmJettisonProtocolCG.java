package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.arm.armcg.ArmHoldC;
import frc.robot.commands.arm.armcg.ClawJettisonC;
import frc.robot.subsystems.ArmS.ArmLevel;

  /**
   * Jettison Protocol
   * 
   * 1. Hold PID at set point
   * 2. While Jettisoning Cargo
   * 3. Sets home position, then moves home 
   */
public class ArmJettisonProtocolCG extends CommandGroup {
  public ArmJettisonProtocolCG() {
    addParallel(new ArmHoldC());
    addSequential(new ClawJettisonC(), 1);
    addSequential(new ArmLevelProtocolC(ArmLevel.ARM_HOME));
  }
}
