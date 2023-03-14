package seedu.duke.diagnosis.illnesses.immunological;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class GeneralFever extends Illness {
    public GeneralFever() {
        super("Fever", 2, false, new ArrayList<>(
                List.of(Symptom.FEVER
                ))
        );
    }
}
