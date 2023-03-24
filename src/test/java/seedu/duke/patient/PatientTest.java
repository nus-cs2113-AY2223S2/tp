//@@author Geeeetyx
package seedu.duke.patient;

import org.junit.jupiter.api.Test;
import seedu.duke.ui.Information;

import java.util.ArrayList;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatientTest {
    private String testName = "Akshay";
    private String testPassword = "iloveCS2113";
    private ArrayList<String> testDiagnosisHistory = new ArrayList<>();
    private Hashtable<String, ArrayList<String>> testMedicineHistory = new Hashtable<>();

    @Test
    public void createNewPatientTest() {

        ArrayList<String> dummyDiagnosisHistory = new ArrayList<>();

        Patient testPatient = new Patient(
                testName, Information.hashPassword(testPassword), testDiagnosisHistory, testMedicineHistory);

        assertEquals(testPatient.getName(), "Akshay");
        assertEquals(testPatient.getPassword(), Information.hashPassword("iloveCS2113"));
        assertEquals(dummyDiagnosisHistory, testPatient.getPatientDiagnosisHistory());

    }

    @Test
    public void testUpdatePatientDiagnosisHistory() {

        ArrayList<String> dummyPatientDiagnosisHistory = new ArrayList<>();
        dummyPatientDiagnosisHistory.add("Flu");
        dummyPatientDiagnosisHistory.add("Fever");
        dummyPatientDiagnosisHistory.add("COVID-19");

        Patient testPatient = new Patient(
                testName, Information.hashPassword(testPassword), testDiagnosisHistory, testMedicineHistory);

        testPatient.updatePatientDiagnosisHistory("Flu");
        testPatient.updatePatientDiagnosisHistory("Fever");
        testPatient.updatePatientDiagnosisHistory("COVID-19");

        assertEquals(dummyPatientDiagnosisHistory, testPatient.getPatientDiagnosisHistory());

    }
}
//@@author
