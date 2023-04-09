package seedu.duke.diagnosis.illnesses.physical;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class SuperficialWound extends Illness {

    public SuperficialWound() {
        super("Superficial Wound", 1, false, new ArrayList<>(
                List.of(Symptom.WOUND_OR_CUT,
                        Symptom.BLEEDING,
                        Symptom.PUSS
                ))
        );
    }
}
