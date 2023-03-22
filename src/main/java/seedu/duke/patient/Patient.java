/**
 * @author Geeeetyx
 */
package seedu.duke.patient;

import seedu.duke.medicine.Medicine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class Patient {
    protected ArrayList<String> patientDiagnosisHistory = new ArrayList<>();
    protected Hashtable<String, ArrayList<Medicine>> patientMedicineHistory = new Hashtable<>();
    protected String name;
    protected int hash;

    public Patient(String name, int hash, ArrayList<String> patientDiagnosisHistory) {
        this.setName(name);
        this.setPassword(hash);
        this.setPatientDiagnosisHistory(patientDiagnosisHistory);
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

    public void setPatientDiagnosisHistory(ArrayList<String> diagnosisHistory) {
        patientDiagnosisHistory = diagnosisHistory;
    }

    public ArrayList<String> getPatientDiagnosisHistory() {
        return patientDiagnosisHistory;
    }

    public void updatePatientDiagnosisHistory(String diagnosis) {
        patientDiagnosisHistory.add(diagnosis);
    }

    /**
     * Updates patient's medicine history.
     * @author tanyizhe, Brennanzuz
     * @param medicines ArrayList describing medicines patient has been prescribed
     */
    public void updatePatientMedicineHistory(String date, ArrayList<Medicine> medicines) {
        //TODO: Account for no medication being available
        //I just placed this here to prevent a crash.
        try {
            if (patientMedicineHistory.containsKey(date)) {
                appendMedicineToSameDate(date, medicines);
            } else {
                patientMedicineHistory.put(date, medicines);
            }
        } catch (NullPointerException exception) {
            System.out.println("No medication is available.");
        }

    }
    /**
     * Appends medicine patient is prescribed if additional medicine is prescribed on the same day
     * @author tanyizhe
     * @param medicines ArrayList describing medicines patient has been prescribed
     * @param date String representing the date that patients were prescribed medication
     */
    private void appendMedicineToSameDate(String date, ArrayList<Medicine> medicines) {
        for (Medicine medicine : medicines) {
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
}

