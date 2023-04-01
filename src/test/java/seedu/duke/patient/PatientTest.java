//@@author Geeeetyx
package seedu.duke.patient;

import org.junit.jupiter.api.Test;
import seedu.duke.ui.Information;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class of test methods to test the Patient class's methods.
 */
public class PatientTest {
    private final String testName = "Akshay";
    private final String testPassword = "iloveCS2113";
    private final ArrayList<String> testDiagnosisHistory = new ArrayList<>();
    private final Hashtable<String, ArrayList<String>> testMedicineHistory = new Hashtable<>();

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

    @Test
    public void testUpdatePatientMedicineHistory() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();

        String testDate = dtf.format(now);

        Hashtable<String, ArrayList<String>> dummyMedicineHistory = new Hashtable<>();
        ArrayList<String> dummyMedicineArrayList = new ArrayList<>();

        dummyMedicineArrayList.add("Ultracarbon");
        dummyMedicineArrayList.add("Paracetamol");
        dummyMedicineArrayList.add("Ibuprofen");
        dummyMedicineArrayList.add("Aspirin");

        dummyMedicineHistory.put(testDate, dummyMedicineArrayList);

        Patient testPatient = new Patient(
                testName, Information.hashPassword(testPassword), testDiagnosisHistory, testMedicineHistory);

        testPatient.updatePatientMedicineHistory(testDate, dummyMedicineArrayList);

        assertEquals(dummyMedicineHistory, testPatient.getPatientMedicineHistory());
    }
}
//@@author
