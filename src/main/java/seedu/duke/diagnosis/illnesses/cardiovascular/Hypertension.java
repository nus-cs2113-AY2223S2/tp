package seedu.duke.diagnosis.illnesses.cardiovascular;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class Hypertension extends Illness {
    public Hypertension() {
        super("Hypertension (High Blood Pressure)", 3, true, new ArrayList<>(
                List.of(Symptom.HIGH_HEART_RATE,
                        Symptom.HEAD_ACHE,
                        Symptom.BLURRED_VISION,
                        Symptom.SENSITIVITY_TO_LIGHT_AND_SOUND,
                        Symptom.FEVER
                ))
        );
    }
}
