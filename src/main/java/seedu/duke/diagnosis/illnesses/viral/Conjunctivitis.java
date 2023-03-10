package seedu.duke.diagnosis.illnesses.viral;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class Conjunctivitis extends Illness {
    public Conjunctivitis() {
        super("Conjunctivitis", 2, false, new ArrayList<>(
                List.of(Symptom.ITCHY_EYE,
                        Symptom.RED_EYES
                ))
        );
    }
}
