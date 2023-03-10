package seedu.duke.diagnosis.illnesses.bacterial;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class Tuberculosis extends Illness {
    public Tuberculosis() {
        super("Tuberculosis", 3, true, new ArrayList<>(
                List.of(Symptom.DRY_COUGH,
                        Symptom.COUGH_WITH_PHLEGM,
                        Symptom.BLEEDING_WHEN_COUGHING,
                        Symptom.CHEST_PAIN
                ))
        );
    }
}
