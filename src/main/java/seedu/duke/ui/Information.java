//@@author Jeraldchen
package seedu.duke.ui;

import seedu.duke.patient.Patient;

import java.util.HashMap;

public class Information {
    public static HashMap<String, Patient> patientsList = new HashMap<>();
    public void storePatientInfo(String password, Patient patient) { //storePatientInfo(personalInfo(name), patient)
        patientsList.put(password, patient);
    }
    public Patient getPatientInfo(String password) {
        return patientsList.get(password);
    }

    public void printDiagnosisHistory(String password) {
        Patient patient = patientsList.get(password);
        System.out.println("Your diagnosis history is: ");
        for (int i = 0; i < patient.getPatientDiagnosisHistory().size(); i++) {
            System.out.println(patient.getPatientDiagnosisHistory().get(i));
        }
    }

}
