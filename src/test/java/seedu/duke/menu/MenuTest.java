//@@author Jeraldchen
package seedu.duke.menu;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuTest {
    //@@author Jeraldchen
    @Test
    public void registerPatient() {
        String name = "Tom";
        String password1 = "iloveCS2113";
        String password2 = "iloveCS2113";

        HashMap<String, String> personalInfo = new HashMap<>();
        if (password1 == password2) {
            personalInfo.put(name, password1);
        }

        assertEquals(personalInfo.get(name), password1);
    }

    //@@author Jeraldchen
    @Test
    public void addSymptomsToSymptomsList() {
        String symptom = "Fever";
        String symptom2 = "Cough";
        ArrayList<String> symptomsList = new ArrayList<>();
        symptomsList.add(symptom);
        symptomsList.add(symptom2);
        assertEquals(symptomsList.get(0), symptom);
        assertEquals(symptomsList.get(1), symptom2);
    }

}
