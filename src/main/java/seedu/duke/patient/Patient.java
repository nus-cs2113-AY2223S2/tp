/**
 * @author Geeeetyx
 */
package seedu.duke.patient;

import java.util.ArrayList;

public class Patient {
    protected ArrayList<String> patientDiagnosisHistory = new ArrayList<>();
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
}

