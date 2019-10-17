package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ClimbCancelC;
import frc.robot.commands.ClimbProtocolCG;
import frc.robot.controllermap.BBoard;
import frc.robot.controllermap.JStick;

/**
 * Operator Interface
 */
public class OI {

  //We are using Joystick and XBox controllers
  public final JStick jStick = new JStick(RobotMap.OI_JSTICK);
  public final XboxController xBox = new XboxController(RobotMap.OI_XBOX);
  public final BBoard bBoard = new BBoard(RobotMap.OI_BBOARD);

  /**
   * Note:
   * Buttons for jStick
   * all button numbers correspond to buttons mapped in image found here: 
   * https://samepage.io/app/#!/55f7d003b627e89b5d0b0c11258e2624567de58f/team-a4f52613587a4eddaeb9a554ab5ee00dac038e98/files/preview-796107915031679377
   * 
   * Buttons for xBox
   * img not available as of yet
   * 
   * Buttons for bBoard
   * all button numbers correspond to buttons mapped in image found here:
   * https://samepage.io/app/#!/55f7d003b627e89b5d0b0c11258e2624567de58f/team-a4f52613587a4eddaeb9a554ab5ee00dac038e98/files/preview-750082345522555459
   */
  public OI() {
    // Climb Buttons
    jStick.button_3_runOnPress(new ClimbProtocolCG());
    jStick.button_10_runOnPress(new ClimbCancelC());  
  }
}
