package seedu.duke.diagnosis.illnesses.physical;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class InternalBleeding extends Illness {
    public InternalBleeding() {
        super("Internal Bleeding", 3, false, new ArrayList<>(
                List.of(Symptom.GENERAL_PAIN,
                        Symptom.GENERAL_SWELLING,
                        Symptom.PALENESS_OF_SKIN,
                        Symptom.THIRST,
                        Symptom.BREATHLESSNESS,
                        Symptom.NAUSEA
                ))
        );
    }
}
