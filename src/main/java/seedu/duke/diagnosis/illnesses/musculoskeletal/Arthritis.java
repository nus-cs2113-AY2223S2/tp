package seedu.duke.diagnosis.illnesses.musculoskeletal;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class Arthritis extends Illness {
    public Arthritis() {
        super("General Arthritis", 1, true, new ArrayList<>(
                List.of(Symptom.JOINT_PAIN,
                        Symptom.SWOLLEN_JOINTS
                ))
        );
    }
}
