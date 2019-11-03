package frc.robot;

import frc.robot.base.NomadDoublePreference;

/**
 * Store all software constants here
 */
public class RobotPreferences {
    //Climb preferences
    public static final NomadDoublePreference ultrasonicHeightLevel2 = new NomadDoublePreference("Climb Ultrasonic Level 2 Height", 15);
    public static final NomadDoublePreference ultrasonicHeightLevel3 = new NomadDoublePreference("Climb Ultrasonic Level 3 Height", 20);
    public static final NomadDoublePreference ultrasonicHeightInterrupted = new NomadDoublePreference("Climb Ultrasonic Interrupted", 1);

    public static final NomadDoublePreference cargoIntakeSpeed = new NomadDoublePreference("Cargo Intake Speed", -0.7);
    public static final NomadDoublePreference cargoShootSpeed = new NomadDoublePreference("Cargo Shooter Speed", 0.9);
}
