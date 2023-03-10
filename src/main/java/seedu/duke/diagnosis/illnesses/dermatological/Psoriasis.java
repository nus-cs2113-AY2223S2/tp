package seedu.duke.diagnosis.illnesses.dermatological;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class Psoriasis extends Illness {

    public Psoriasis() {
        super("Psoriasis", 1, true, new ArrayList<>(
                List.of(Symptom.ITCHY_SKIN,
                        Symptom.DRY_SCALING_SKIN,
                        Symptom.RED_SKIN
                ))
        );
    }
}
