package frc.robot;

import frc.robot.commands.arm.ArmCancelProtocolC;
import frc.robot.commands.arm.ArmJettisonProtocolCG;
import frc.robot.commands.arm.ArmLevelProtocolC;
import frc.robot.commands.arm.ClawIntakeC;
import frc.robot.commands.climb.ClimbCancelC;
import frc.robot.commands.climb.ClimbDeployC;
import frc.robot.commands.climb.MoveC;
import frc.robot.commands.climb.RetractFrontC;
import frc.robot.commands.climb.RetractRearC;
import frc.robot.controllermap.JStick;
import frc.robot.controllermap.XBox;
import frc.robot.subsystems.ArmS.ArmLevel;

/**
 * Operator Interface
 */
public class OI {

  //We are using Joystick and XBox controllers
  public final XBox xBox = new XBox(RobotMap.OI_XBOX);
  public final JStick jStick = new JStick(RobotMap.OI_JSTICK);

  /**
   * Note:
   * Buttons for joystick
   * all button numbers correspond to buttons mapped in image found here: 
   * https://samepage.io/app/#!/55f7d003b627e89b5d0b0c11258e2624567de58f/team-a4f52613587a4eddaeb9a554ab5ee00dac038e98/files/preview-796107915031679377
   * Buttons for XBox
   * img not available as of yet
   */
  public OI() {
      // Arm buttons
      jStick.button_6_runOnPress(new ClawIntakeC());
      jStick.button_8_runOnPress(new ArmLevelProtocolC(ArmLevel.ARM_SHIP));
      jStick.button_11_runOnPress(new ArmLevelProtocolC(ArmLevel.ARM_ROCKET));
      jStick.button_9_runOnPress(new ArmLevelProtocolC(ArmLevel.ARM_REVERSE_SHIP));
      jStick.button_12_runOnPress(new ArmLevelProtocolC(ArmLevel.ARM_REVERSE_ROCKET));
      jStick.button_5_runOnPress(new ArmJettisonProtocolCG());
      jStick.button_7_runOnPress(new ArmCancelProtocolC());

      // Climb buttons for auto mechanism
      //jStick.button_3_runOnPress(new ClimbProtocolCG());
      //jStick.button_10_runOnPress(new ClimbCancelC());

      // "dumb" buttons for the Climbing Mechanism
      //deploys wheels
      xBox.a_runOnPressed(new ClimbDeployC());
      xBox.b_runOnPressed(new ClimbCancelC());
      xBox.x_runOnPressed(new RetractFrontC());
      xBox.y_runOnPressed(new RetractRearC());
      // Moves Forward
      xBox.dpad_up_runWhileHeld(new MoveC(0.7));
      // Moves Backwards
      xBox.dpad_up_left_runWhileHeld(new MoveC(-0.7));
  }
}
