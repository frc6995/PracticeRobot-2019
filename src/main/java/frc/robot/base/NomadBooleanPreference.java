package frc.robot.base;

import edu.wpi.first.wpilibj.Preferences;

/**
 * A class to create double type Preferences we can edit on Shuffleboard
 */
public class NomadBooleanPreference extends NomadPreference {
    public boolean m_defaultValue;
    protected String m_name;

    /**
     * Create a preference with a name and default value
     * @param name
     * @param defaultValue
     */
    public NomadBooleanPreference(String name, boolean defaultValue) {
        m_defaultValue = defaultValue;
        m_name = name;
    }

    /**
     * @return Either the default or the values saved in preferences
     */
    public boolean getValue() {
        if (isUsingDefaults()) {
            return m_defaultValue;
        } else {
            return Preferences.getInstance().getBoolean(m_name, m_defaultValue);
        }
    }
}
