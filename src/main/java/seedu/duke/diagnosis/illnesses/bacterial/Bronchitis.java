package seedu.duke.diagnosis.illnesses.bacterial;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class Bronchitis extends Illness {
    public Bronchitis() {
        super("Bronchitis", 2, true, new ArrayList<>(
                List.of(Symptom.THROAT_IRRITATION,
                        Symptom.HEAD_ACHE,
                        Symptom.RUNNY_NOSE,
                        Symptom.FATIGUE
                ))
        );
    }
}
