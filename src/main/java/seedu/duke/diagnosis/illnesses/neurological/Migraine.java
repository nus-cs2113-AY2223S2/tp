package seedu.duke.diagnosis.illnesses.neurological;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class Migraine extends Illness {
    public Migraine() {
        super("Migraine", 3, true, new ArrayList<>(
                List.of(Symptom.BLURRED_VISION,
                        Symptom.FATIGUE,
                        Symptom.SENSITIVITY_TO_LIGHT_AND_SOUND,
                        Symptom.HEAD_ACHE,
                        Symptom.NAUSEA
                ))
        );
    }
}
