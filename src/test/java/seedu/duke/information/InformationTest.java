//@@author Jeraldchen
package seedu.duke.information;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InformationTest {
    //@@author Jeraldchen
    @Test
    public void resetDiagosisHistory() {
        ArrayList<String> diagnosisHistory = new ArrayList<>();
        diagnosisHistory.add("Diagnosis 1");
        diagnosisHistory.add("Diagnosis 2");
        diagnosisHistory.clear();
        assertEquals(diagnosisHistory.size(), 0);
    }

    //@@author Jeraldchen
    @Test
    public void checkPassword() {
        HashMap<String, String> personalInfo = new HashMap<>();
        String password = "iloveCS2113";
        String name = "Akshay";
        personalInfo.put(password, name);
        assertEquals(personalInfo.containsKey(password), true);

    }

    //@@author Jeraldchen
    @Test
    public void resetSymptoms() {
        ArrayList<String> symptoms = new ArrayList<>();
        symptoms.add("Symptom 1");
        symptoms.add("Symptom 2");
        symptoms.clear();
        assertEquals(symptoms.size(), 0);
    }
}
