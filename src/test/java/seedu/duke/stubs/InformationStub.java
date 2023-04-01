//@@author Thunderdragon221
package seedu.duke.stubs;

import seedu.duke.patient.Patient;

import java.util.ArrayList;
import java.util.HashMap;

public class InformationStub {

    public static HashMap<Integer, Patient> getAllPatientData() {
        HashMap<Integer, Patient> dummyPatientsList = new HashMap<>();
        dummyPatientsList.put(PatientStub.getHash(), PatientStub.getDummyPatient());
        return dummyPatientsList;
    }

    public static ArrayList<String> getQueueList() {
        ArrayList<String> queueList = new ArrayList<>();
        queueList.add("1");
        return queueList;
    }
}
