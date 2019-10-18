package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.climb.climbcg.DeployC;
import frc.robot.commands.climb.climbcg.MoveC;
import frc.robot.commands.climb.climbcg.RetractFrontC;
import frc.robot.commands.climb.climbcg.RetractRearC;

/**
 * Climb Protocol
 */
public class ClimbProtocolCG extends CommandGroup {

  public ClimbProtocolCG() {
    addSequential(new DeployC());       // Module 1: Deploys the Wheels
    addSequential(new MoveC(0.0, 0.0)); // Module 2: Moves forward until timed out, or interrupted
    addParallel(new RetractFrontC());   // Module 3: When Front wheels are over podium, retract them
    addSequential(new MoveC(0.0, 0.0)); // Module 4: Moves forward until timed out, or interrupted
    addParallel(new RetractRearC());    // Module 5: When Center of Gravity is over podium, retract Rear
    addSequential(new MoveC(0.0, 0.0)); // Module 6: Move Forward onto podium
  }
}
