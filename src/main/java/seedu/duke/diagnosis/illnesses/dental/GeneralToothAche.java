package seedu.duke.diagnosis.illnesses.dental;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class GeneralToothAche extends Illness {
    public GeneralToothAche() {
        super("Tooth Ache", 1, true, new ArrayList<>(
                List.of(Symptom.TOOTH_ACHE
                ))
        );
    }
}
