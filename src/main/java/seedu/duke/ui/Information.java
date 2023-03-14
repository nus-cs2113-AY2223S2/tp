package seedu.duke.ui;

import seedu.duke.patient.Patient;

import java.util.HashMap;

import static seedu.duke.save.Storage.saveData;

/**
 * This class stores information on all patients registered by DoctorDuke.
 * @author Jeraldchen
 */
public class Information {
    private static HashMap<String, Patient> patientsList = new HashMap<>();

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

    /**
     * Resets the diagnosis history of the patient.
     *
     * @param password The password of the patient.
     */
    public static void resetDiagnosisHistory(String password) {
        Patient patient = patientsList.get(password);
        patient.getPatientDiagnosisHistory().clear();
        assert patient.getPatientDiagnosisHistory().size() == 0 : "Diagnosis history should be empty";
        System.out.println("Your diagnosis history has been reset.");
        saveData();
    }

    /**
     * Checks the existence of a password in Dr Duke.
     * @author Thunderdragon221
     *
     * @param password password to check.
     * @return true if password exists in Dr Duke, and false otherwise.
     */
    public static boolean checkPassword(String password) {
        return patientsList.containsKey(password);
    }

    /**
     * Returns all patient data currently stored in Dr Duke.
     *
     * @return Hashmap of all patient data.
     */
    public static HashMap<String, Patient> getAllPatientData() {
        return patientsList;
    }
}
