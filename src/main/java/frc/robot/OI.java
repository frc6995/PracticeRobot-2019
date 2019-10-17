package frc.robot;

import frc.robot.controllermap.JStick;

/**
 * Operator Interface
 */
public class OI {

  //We are using Joystick and XBox controllers
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


      // jStick.button_6_runOnPress(new ClawIntakeC());
      // jStick.button_8_runOnPress(new ArmLevelProtocolC(ArmLevel.ARM_SHIP));
      // jStick.button_11_runOnPress(new ArmLevelProtocolC(ArmLevel.ARM_ROCKET));
      // jStick.button_9_runOnPress(new ArmLevelProtocolC(ArmLevel.ARM_REVERSE_SHIP));
      // jStick.button_12_runOnPress(new ArmLevelProtocolC(ArmLevel.ARM_REVERSE_ROCKET));
      // jStick.button_5_runOnPress(new ArmJettisonProtocolCG());
      // jStick.button_7_runOnPress(new ArmCancelProtocolC());

      // Climb buttons for auto mechanism
      //jStick.button_3_runOnPress(new ClimbProtocolCG());
      //jStick.button_10_runOnPress(new ClimbCancelC());




      
  }
}
