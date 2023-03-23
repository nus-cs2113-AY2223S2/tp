//@@Brennanzuz
package seedu.duke.diagnosis.illnesses;

import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;

/**
 * A generic class to be inherited by all other illnesses. Its constructor class will be filled by predefined constants
 * when inherited by other classes to match the desired illness.
 * @author Brennanzuz
 */
public class Illness {
    private final String illnessName;
    private final int severityLevel;
    private final boolean isChronic;
    private final ArrayList<Symptom> symptoms;

    public Illness(String illnessName, int severityLevel, boolean isChronic, ArrayList<Symptom> symptoms) {
        this.illnessName = illnessName;
        this.severityLevel = severityLevel;
        this.isChronic = isChronic;
        this.symptoms = symptoms;
    }

    public String getIllnessName() {
        return illnessName;
    }

    public int getSeverityLevel() {
        return severityLevel;
    }

    public boolean isChronic() {
        return isChronic;
    }

    public ArrayList<Symptom> getSymptoms() {
        return symptoms;
    }
}
