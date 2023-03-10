package seedu.duke.diagnosis.illnesses.physical;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class Fracture extends Illness {
    public Fracture() {
        super("Fracture", 2, false, new ArrayList<>(
                List.of(Symptom.JOINT_PAIN,
                        Symptom.FRACTURE
                ))
        );
    }
}
