//@@author Thunderdragon221
package seedu.duke.stubs;

import seedu.duke.patient.Patient;

import java.util.ArrayList;
import java.util.Hashtable;

public class PatientStub {

    public static Patient getDummyPatient() {
        Hashtable<String, ArrayList<String>> dummyPatientDiagnosisHistory = new Hashtable<>();
        ArrayList<String> dummydiagnoses = new ArrayList<>();
        dummydiagnoses.add("Fever");
        dummyPatientDiagnosisHistory.put("2023/01/01", dummydiagnoses);

        Hashtable<String, ArrayList<String>> dummyPatientMedicineHistory = new Hashtable<>();
        ArrayList<String> dummyMedicines = new ArrayList<>();
        dummyMedicines.add("Paracetamol");
        dummyPatientMedicineHistory.put("2023/01/01", dummyMedicines);

        String dummyName = "Jerry";
        int dummyHash = 1;
        return new Patient(dummyName, dummyHash, dummyPatientDiagnosisHistory,
                dummyPatientMedicineHistory);
    }

    public static int getHash() {
        return 1;
    }
}
