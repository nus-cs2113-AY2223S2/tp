package seedu.duke.diagnosis.illnesses.musculoskeletal;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class GeneralMuscleAche extends Illness {
    public GeneralMuscleAche() {
        super("Muscle Ache", 1, false, new ArrayList<>(
                List.of(Symptom.MUSCLE_ACHE
                ))
        );
    }
}
