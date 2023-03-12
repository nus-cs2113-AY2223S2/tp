package seedu.duke.ui;

import seedu.duke.patient.Patient;

import java.util.HashMap;

/**
 * @author Jeraldchen
 *     This class stores information on all patients registered by DoctorDuke.
 */
public class Information {
    public static HashMap<String, Patient> patientsList = new HashMap<>();

    //storePatientInfo(personalInfo(name), patient)
    public static void storePatientInfo(String password, Patient patient) {
        patientsList.put(password, patient);
    }

    public static Patient getPatientInfo(String password) {
        return patientsList.get(password);
    }

    public static void printDiagnosisHistory(String password) {
        Patient patient = patientsList.get(password);
        System.out.println("Your diagnosis history is: ");
        for (int i = 0; i < patient.getPatientDiagnosisHistory().size(); i++) {
            System.out.println(patient.getPatientDiagnosisHistory().get(i));
        }
    }

    public static void resetDiagnosisHistory(String password) {
        Patient patient = patientsList.get(password);
        patient.getPatientDiagnosisHistory().clear();
    }

}
