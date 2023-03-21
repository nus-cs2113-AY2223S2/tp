package seedu.duke.ui;

import seedu.duke.diagnosis.symptoms.Symptom;
import seedu.duke.patient.Patient;

import java.util.ArrayList;
import java.util.HashMap;

import static seedu.duke.save.Storage.saveData;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class stores information on all patients registered by DoctorDuke.
 * @author Jeraldchen
 */
public class Information {
    private static HashMap<Integer, Patient> patientsList = new HashMap<>();

    private static Logger logger = Logger.getLogger(Information.class.getName());

    //storePatientInfo(personalInfo(name), patient)
    public static void storePatientInfo(int hash, Patient patient) {
        logger.log(Level.INFO, "Storing patient information");
        patientsList.put(hash, patient);
    }

    public static Patient getPatientInfo(int hash) {
        logger.log(Level.INFO, "Retrieving patient information");
        return patientsList.get(hash);
    }

    public static void printDiagnosisHistory(int hash) {
        logger.log(Level.INFO, "Printing diagnosis history");
        Patient patient = patientsList.get(hash);
        System.out.println("Your diagnosis history is: ");
        for (int i = 0; i < patient.getPatientDiagnosisHistory().size(); i++) {
            System.out.println(patient.getPatientDiagnosisHistory().get(i));
        }
    }

    /**
     * Resets the diagnosis history of the patient.
     *
     * @param hash The hashed password of the patient.
     */
    public static void resetDiagnosisHistory(int hash) {
        logger.log(Level.INFO, "Resetting diagnosis history");
        Patient patient = patientsList.get(hash);
        patient.getPatientDiagnosisHistory().clear();
        assert patient.getPatientDiagnosisHistory().size() == 0 : "Diagnosis history should be empty";
        System.out.println("Your diagnosis history has been reset.");
        saveData();
    }

    /**
     * Checks the existence of a password in Dr Duke.
     * @author Thunderdragon221
     *
     * @param hash hashedPassword to check.
     * @return true if hashedPassword exists in Dr Duke, and false otherwise.
     */
    public static boolean checkHash(int hash) {
        logger.log(Level.INFO, "Checking hash");
        return patientsList.containsKey(hash);
    }

    /**
     * Returns all patient data currently stored in Dr Duke.
     *
     * @return Hashmap of all patient data.
     */
    public static HashMap<Integer, Patient> getAllPatientData() {
        logger.log(Level.INFO, "Retrieving all patient data");
        return patientsList;
    }

    /**
     * Resets the symptom choice of the patient.
     * @author Jeraldchen
     * @param symptoms The symptom choice of the patient.
     */
    public static void resetSymptomChoice(ArrayList<Symptom> symptoms) {
        if (symptoms.size() != 0) {
            symptoms.clear();
            System.out.println("Your symptom choice has been reset.");
        }
    }

    /**
     * Hashes the password keyed in by the user.
     * @author Thunderdragon221
     *
     * @param password password to hash.
     * @return hash of password.
     */
    public static int hashPassword(String password) {
        return password.hashCode();
    }
}
