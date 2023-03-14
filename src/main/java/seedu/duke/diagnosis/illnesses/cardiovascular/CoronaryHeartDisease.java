package seedu.duke.diagnosis.illnesses.cardiovascular;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class CoronaryHeartDisease extends Illness {
    public CoronaryHeartDisease() {
        super("Coronary Heart Disease", 3, true, new ArrayList<>(
                List.of(Symptom.HIGH_HEART_RATE,
                        Symptom.CHEST_PAIN,
                        Symptom.BREATHLESSNESS,
                        Symptom.NAUSEA,
                        Symptom.CHILLS
                ))
        );
    }
}
