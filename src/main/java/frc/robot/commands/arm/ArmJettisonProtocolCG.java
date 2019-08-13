package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.arm.armcg.ArmHoldC;
import frc.robot.commands.arm.armcg.ArmHomeC;
import frc.robot.commands.arm.armcg.ClawJettisonC;

public class ArmJettisonProtocolCG extends CommandGroup {
  /**
   *  jettison protocol while holding pid, sequntials should be fine tuned
   */
  public ArmJettisonProtocolCG() {
    addParallel(new ArmHoldC());
    addSequential(new ClawJettisonC(), 1);
    addSequential(new WaitCommand(1));
    addSequential(new ArmHomeC());
  }
}
