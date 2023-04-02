//@@author Geeeetyx
package seedu.duke.patient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

/**
 * The Patient Class contains methods related to the information of a Patient.
 */
public class Patient {
    protected Hashtable<String, ArrayList<String>> patientDiagnosisHistory = new Hashtable<>();
    protected Hashtable<String, ArrayList<String>> patientMedicineHistory = new Hashtable<>();
    protected String name;
    protected int hash;

    /**
     * Creates a new Patient object, to be used throughout Dr Duke.
     * @param name                    The name of the Patient.
     * @param hash                    The hashed password of the Patient.
     * @param patientDiagnosisHistory Array containing the history of diagnoses of the patient.
     * @param patientMedicineHistory  Array containing the history of prescribed medications to the patient.
     */
    public Patient(String name, int hash, Hashtable<String, ArrayList<String>> patientDiagnosisHistory,
                   Hashtable<String, ArrayList<String>> patientMedicineHistory
                   //int queueNumber
                   ) {
        assert patientDiagnosisHistory.size() >= 0 : "Patient diagnosis history is either empty or not empty";
        this.setName(name);
        this.setPassword(hash);
        this.setPatientDiagnosisHistory(patientDiagnosisHistory);
        this.setPatientMedicineHistory(patientMedicineHistory);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassword() {
        return hash;
    }

    public void setPassword(int hash) {
        this.hash = hash;
    }

    public void setPatientDiagnosisHistory(Hashtable<String, ArrayList<String>> diagnosisHistory) {
        patientDiagnosisHistory = diagnosisHistory;
    }

    public Hashtable<String, ArrayList<String>> getPatientDiagnosisHistory() {
        return patientDiagnosisHistory;
    }

    //@@author Thunderdragon221
    /**
     * Updates patient's diagnosis history.
     * @param date date of use of Dr Duke.
     * @param diagnoses ArrayList containing list of illnesses patient is diagnosed with.
     */
    public void updatePatientDiagnosisHistory(String date, ArrayList<String> diagnoses) {
        if (patientDiagnosisHistory.containsKey(date)) {
            appendDiagnosisToSameDate(date, diagnoses);
        } else {
            patientDiagnosisHistory.put(date, diagnoses);
        }
    }

    /**
     * Appends illnesses patient is diagnosed with if additional illnesses are diagnosed on the same day.
     * @param diagnoses ArrayList of illnesses patient is diagnosed with.
     * @param date String representing the date that patient was diagnosed.
     */
    private void appendDiagnosisToSameDate(String date, ArrayList<String> diagnoses) {
        for (String diagnosis : diagnoses) {
            if (!patientDiagnosisHistory.get(date).contains(diagnosis)) {
                patientDiagnosisHistory.get(date).add(diagnosis);
            }
        }
    }

    //@@author tanyizhe
    /**
     * Updates patient's medicine history.
     * @param medicines ArrayList describing medicines patient has been prescribed
     */
    public void updatePatientMedicineHistory(String date, ArrayList<String> medicines) {
        if (patientMedicineHistory.containsKey(date)) {
            appendMedicineToSameDate(date, medicines);
        } else {
            patientMedicineHistory.put(date, medicines);
        }
    }

    /**
     * Appends medicine patient is prescribed if additional medicine is prescribed on the same day
     * @param medicines ArrayList describing medicines patient has been prescribed
     * @param date String representing the date that patients were prescribed medication
     */
    private void appendMedicineToSameDate(String date, ArrayList<String> medicines) {
        for (String medicine : medicines) {
            if (!patientMedicineHistory.get(date).contains(medicine)) {
                patientMedicineHistory.get(date).add(medicine);
            }
        }
    }

    /**
     * Prints the medication history of patient.
     */
    public void printPatientMedicineHistory() {
        System.out.println("---------------------------------------------------");
        System.out.println("Medication History:");
        List<String> dates = Collections.list(patientMedicineHistory.keys());
        Collections.sort(dates);
        for (String date : dates) {
            System.out.println(date + ": " + patientMedicineHistory.get(date));
        }
    }
    public Hashtable<String, ArrayList<String>> getPatientMedicineHistory() {
        return patientMedicineHistory;
    }

    public void setPatientMedicineHistory(Hashtable<String, ArrayList<String>> patientMedicineHistory) {
        this.patientMedicineHistory = patientMedicineHistory;
    }
}

