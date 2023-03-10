package seedu.duke.diagnosis.illnesses.dermatological;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class Hives extends Illness {
    public Hives() {
        super("Hives", 2, false, new ArrayList<>(
                List.of(Symptom.RED_SKIN,
                        Symptom.ITCHY_SKIN,
                        Symptom.SWELLING_SKIN
                ))
        );
    }
}
