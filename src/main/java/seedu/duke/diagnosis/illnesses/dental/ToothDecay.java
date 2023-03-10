package seedu.duke.diagnosis.illnesses.dental;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class ToothDecay extends Illness {
    public ToothDecay() {
        super("Tooth Decay", 2, true, new ArrayList<>(
                List.of(Symptom.BLACK_TEETH,
                        Symptom.TOOTH_ACHE
                ))
        );
    }
}
