//@@Geeeetyx
package seedu.duke.patient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class Patient {
    protected ArrayList<String> patientDiagnosisHistory = new ArrayList<>();
    protected Hashtable<String, ArrayList<String>> patientMedicineHistory = new Hashtable<>();
    protected String name;
    protected int hash;

    public Patient(String name, int hash, ArrayList<String> patientDiagnosisHistory,
                   Hashtable<String, ArrayList<String>> patientMedicineHistory) {
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
    public void updatePatientMedicineHistory(String date, ArrayList<String> medicines) {
        if (patientMedicineHistory.containsKey(date)) {
            appendMedicineToSameDate(date, medicines);
        } else {
            patientMedicineHistory.put(date, medicines);
        }
    }

    //@@author tanyizhe

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
        //@@Geeeetyx
        System.out.println("---------------------------------------------------");
        //@@tanyizhe
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

