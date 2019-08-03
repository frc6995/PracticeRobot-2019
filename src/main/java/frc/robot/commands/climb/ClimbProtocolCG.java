package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Protocol for Climbing, planning on this being autonomus
 */
public class ClimbProtocolCG extends CommandGroup {
  public ClimbProtocolCG() {
    addSequential(new DeployC());
    addSequential(new MoveForwardC());
    addSequential(new RetractFrontC());
    addSequential(new MoveForwardC());
    addSequential(new RetractRearC());
    addSequential(new MoveForwardC());
  }
}
