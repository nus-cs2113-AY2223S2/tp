package seedu.duke.diagnosis.illnesses.immunological;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class Diabetes extends Illness {
    public Diabetes() {
        super("Diabetes", 3, true, new ArrayList<>(
                List.of(Symptom.THIRST,
                        Symptom.FATIGUE,
                        Symptom.SLOW_REGENERATION,
                        Symptom.BLURRED_VISION,
                        Symptom.FREQUENT_URINATION,
                        Symptom.WEIGHT_LOSS
                ))
        );
    }
}
