//@@author Thunderdragon221
package seedu.duke.stubs;

import seedu.duke.patient.Patient;

import java.util.HashMap;

public class InformationStub {

    public static HashMap<Integer, Patient> getAllPatientData() {
        HashMap<Integer, Patient> dummyPatientsList = new HashMap<>();
        dummyPatientsList.put(PatientStub.getHash(), PatientStub.getDummyPatient());
        return dummyPatientsList;
    }
}
