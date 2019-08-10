package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbCancelProtocolCG extends CommandGroup {
  public ClimbCancelProtocolCG() {
    addParallel(new RetractFrontC());
    addSequential(new RetractRearC());
  }
}
