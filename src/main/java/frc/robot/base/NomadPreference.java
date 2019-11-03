package frc.robot.base;

/**
 * A class to create Preferences we can edit on Shuffleboard
 */
public abstract class NomadPreference {
    private static boolean use_defaults = false;
    protected String m_name;

    /**
     * Use hard coded values
     */
    public static void useDefaults() {
        use_defaults = true;
    }

    /**
     * Use values saved in preferences
     */
    public static void usePreferences() {
        use_defaults = false;
    }

    /**
     * @return Are we using the defaults?
     */
    public static boolean isUsingDefaults() {
        return use_defaults;
    }
}
