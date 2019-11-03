package frc.robot.base;

import edu.wpi.first.wpilibj.Preferences;

/**
 * A class to create double type Preferences we can edit on Shuffleboard
 */
public class NomadIntPreference extends NomadPreference {
    public int m_defaultValue;
    protected String m_name;

    /**
     * Create a preference with a name and default value
     * @param name
     * @param defaultValue
     */
    public NomadIntPreference(String name, int defaultValue) {
        m_defaultValue = defaultValue;
        m_name = name;
    }

    /**
     * @return Either the default or the values saved in preferences
     */
    public int getValue() {
        if (isUsingDefaults()) {
            return m_defaultValue;
        } else {
            return Preferences.getInstance().getInt(m_name, m_defaultValue);
        }
    }
}
