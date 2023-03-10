package seedu.duke.diagnosis.illnesses.digestive;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class GeneralStomachAche extends Illness {
    public GeneralStomachAche() {
        super("Stomach Ache", 1, false, new ArrayList<>(
                List.of(Symptom.STOMACH_ACHE
                ))
        );
    }
}
