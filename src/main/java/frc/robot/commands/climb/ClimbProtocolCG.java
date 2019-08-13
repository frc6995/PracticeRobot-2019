package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.climb.climbcg.ClimbDeployC;
import frc.robot.commands.climb.climbcg.MoveForwardC;
import frc.robot.commands.climb.climbcg.MoveForwardTimoutC;
import frc.robot.commands.climb.climbcg.RetractFrontC;
import frc.robot.commands.climb.climbcg.RetractRearC;

/**
 * Protocol for Climbing, planning on this being autonomus
 */
public class ClimbProtocolCG extends CommandGroup {
  public ClimbProtocolCG() {
    addSequential(new ClimbDeployC());
    addSequential(new MoveForwardC());
    addSequential(new RetractFrontC());
    addSequential(new MoveForwardC());
    addSequential(new RetractRearC());
    addSequential(new MoveForwardTimoutC());
  }
}
