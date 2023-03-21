//@@author Geeeetyx
package seedu.duke.patient;

import org.junit.jupiter.api.Test;
import seedu.duke.ui.Information;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatientTest {
    @Test
    public void createNewPatientTest() {
        String name = "Akshay";
        String password = "iloveCS2113";

        ArrayList<String> diagnosisHistory = new ArrayList<>();
        diagnosisHistory.add("Flu");
        diagnosisHistory.add("COVID-19");

        Patient testPatient = new Patient(name, Information.hashPassword(password), diagnosisHistory);

        assertEquals(testPatient.getName(), "Akshay");
        assertEquals(testPatient.getPassword(), Information.hashPassword("iloveCS2113"));
        assertEquals(diagnosisHistory, testPatient.getPatientDiagnosisHistory());
    }
}
