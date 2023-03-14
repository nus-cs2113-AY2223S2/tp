package seedu.duke.diagnosis.illnesses.dermatological;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class GeneralRash extends Illness {
    public GeneralRash() {
        super("Rashes", 1, false, new ArrayList<>(
                List.of(Symptom.ITCHY_SKIN,
                        Symptom.RED_SKIN
                ))
        );
    }
}
