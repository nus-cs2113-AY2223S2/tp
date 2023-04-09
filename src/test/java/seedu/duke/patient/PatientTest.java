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
    private final Hashtable<String, ArrayList<String>> testDiagnosisHistory = new Hashtable<>();
    private final Hashtable<String, ArrayList<String>> testMedicineHistory = new Hashtable<>();

    @Test
    public void createNewPatientTest() {

        Hashtable<String, ArrayList<String>> dummyDiagnosisHistory = new Hashtable<>();

        Patient testPatient = new Patient(
                testName, Information.hashPassword(testPassword), testDiagnosisHistory, testMedicineHistory);

        assertEquals(testPatient.getName(), "Akshay");
        assertEquals(testPatient.getPassword(), Information.hashPassword("iloveCS2113"));
        assertEquals(dummyDiagnosisHistory, testPatient.getPatientDiagnosisHistory());

    }

    //@@author Thunderdragon221
    @Test
    public void testUpdatePatientDiagnosisHistory() {

        Hashtable<String, ArrayList<String>> dummyPatientDiagnosisHistory = new Hashtable<>();
        ArrayList<String> dummyPatientDiagnoses = new ArrayList<>();
        dummyPatientDiagnoses.add("Flu");
        dummyPatientDiagnoses.add("Fever");
        dummyPatientDiagnoses.add("COVID-19");
        dummyPatientDiagnosisHistory.put("2023/01/01", dummyPatientDiagnoses);

        Patient testPatient = new Patient(
                testName, Information.hashPassword(testPassword), testDiagnosisHistory, testMedicineHistory);

        ArrayList<String> testPatientDiagnoses = new ArrayList<>();
        testPatientDiagnoses.add("Flu");
        testPatientDiagnoses.add("Fever");
        testPatientDiagnoses.add("COVID-19");
        testPatient.updatePatientDiagnosisHistory("2023/01/01", testPatientDiagnoses);

        assertEquals(dummyPatientDiagnosisHistory, testPatient.getPatientDiagnosisHistory());

    }

    //@@author Geeeetyx
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
