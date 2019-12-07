package frc.robot.base;

import edu.wpi.first.wpilibj.Preferences;

/**
 * A class to create double type Preferences we can edit on Shuffleboard
 */
public class NomadDoublePreference extends NomadPreference {
    public double m_defaultValue;
    protected String m_name;

    /**
     * Create a preference with a name and default value
     * @param name
     * @param defaultValue
     */
    public NomadDoublePreference(String name, double defaultValue) {
        m_defaultValue = defaultValue;
        m_name = name;
        if (!Preferences.getInstance().containsKey(m_name)){
            Preferences.getInstance().putDouble(m_name, m_defaultValue);
        }
    }

    /**
     * @return Either the default or the values saved in preferences
     */
    public double getValue() {
        if (isUsingDefaults()) {
            return m_defaultValue;
        } else {
            return Preferences.getInstance().getDouble(m_name, m_defaultValue);
        }
    }
}
