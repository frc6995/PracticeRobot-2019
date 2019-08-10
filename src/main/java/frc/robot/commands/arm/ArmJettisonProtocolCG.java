package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ArmJettisonProtocolCG extends CommandGroup {
  /**
   *  jettison protocol while holding pid, 
   */
  public ArmJettisonProtocolCG() {
    addParallel(new HoldPidC());
    addSequential(new ClawJettisonC(), 1);
    addSequential(new WaitCommand(1));
    addSequential(new MoveHomeC());
  }
}
