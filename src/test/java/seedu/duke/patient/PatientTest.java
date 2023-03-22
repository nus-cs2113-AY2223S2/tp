//@@author Geeeetyx
package seedu.duke.patient;

import org.junit.jupiter.api.Test;
import seedu.duke.ui.Information;

import java.util.ArrayList;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatientTest {
    @Test
    public void createNewPatientTest() {
        String name = "Tom";
        String password = "iloveCS2113";

        ArrayList<String> diagnosisHistory = new ArrayList<>();
        diagnosisHistory.add("Flu");
        diagnosisHistory.add("COVID-19");

        Hashtable<String, ArrayList<String>> medicineHistory = new Hashtable<>();


        Patient testPatient = new Patient(name, Information.hashPassword(password), diagnosisHistory, medicineHistory);

        assertEquals(testPatient.getName(), "Tom");
        assertEquals(testPatient.getPassword(), Information.hashPassword("iloveCS2113"));
        assertEquals(diagnosisHistory, testPatient.getPatientDiagnosisHistory());
    }
}
