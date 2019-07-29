package frc.robot;

import frc.robot.interfaces.XBox;
import frc.robot.subsystems.cargo.CargoArmS.ArmLevel;
import frc.robot.interfaces.JStick;
import frc.robot.RobotMap;
import frc.robot.commands.arm.CancelProtocolCG;
import frc.robot.commands.arm.LevelProtocolCG;
import frc.robot.commands.arm.YeetProtocolCG;
import frc.robot.commands.claw.ClawIntakeC;

public class OI {

  //input devices
  public final XBox xBox = new XBox(RobotMap.OI_XBOX);
  public final JStick jStick = new JStick(RobotMap.OI_JSTICK);

  public OI() {
      //buttons for joystick, please refer to buttons on image: https://samepage.io/app/#!/55f7d003b627e89b5d0b0c11258e2624567de58f/team-a4f52613587a4eddaeb9a554ab5ee00dac038e98/files/preview-796107915031679377

      //all four levels:
      jStick.button_8_runOnPress(new LevelProtocolCG(ArmLevel.ARM_SHIP));
      jStick.button_11_runOnPress(new LevelProtocolCG(ArmLevel.ARM_ROCKET));
      jStick.button_9_runOnPress(new LevelProtocolCG(ArmLevel.ARM_REVERSE_SHIP));
      jStick.button_12_runOnPress(new LevelProtocolCG(ArmLevel.ARM_REVERSE_ROCKET));

      //trigger yeets
      jStick.button_5_runOnPress(new YeetProtocolCG());

      //cancel level climb
      jStick.button_7_runOnPress(new CancelProtocolCG());

      //reload is currently button 6
      jStick.button_6_runOnPress(new ClawIntakeC());

  }
}
