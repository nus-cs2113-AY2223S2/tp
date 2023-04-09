package seedu.duke.diagnosis.illnesses.musculoskeletal;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class Osteoporosis extends Illness {

    public Osteoporosis() {
        super("Osteoporosis", 2, true, new ArrayList<>(
                List.of(Symptom.JOINT_PAIN,
                        Symptom.FRACTURE,
                        Symptom.HISTORY_OF_OSTEOPOROSIS
                ))
        );
    }
}
