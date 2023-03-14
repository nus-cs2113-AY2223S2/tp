package seedu.duke.diagnosis.illnesses.musculoskeletal;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class GeneralBackAche extends Illness {
    public GeneralBackAche() {
        super("Back Ache", 1, false, new ArrayList<>(
                List.of(Symptom.BACK_ACHE
                ))
        );
    }
}
