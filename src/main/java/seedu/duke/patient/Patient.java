/**
 * @author Geeeetyx
 */
package seedu.duke.patient;

import java.util.ArrayList;

public class Patient {
    public static ArrayList<String> patientDiagnosisHistory;
    protected String name;
    protected String password;

    public Patient(String name, String password, ArrayList<String> patientDiagnosisHistory) {
        this.setName(name);
        this.setPassword(password);
        this.setPatientDiagnosisHistory(patientDiagnosisHistory);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPatientDiagnosisHistory(ArrayList<String> diagnosisHistory) {
        patientDiagnosisHistory = diagnosisHistory;
    }

    public ArrayList<String> getPatientDiagnosisHistory() {
        return patientDiagnosisHistory;
    }
}

