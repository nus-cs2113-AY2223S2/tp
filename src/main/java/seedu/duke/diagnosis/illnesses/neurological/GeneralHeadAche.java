package seedu.duke.diagnosis.illnesses.neurological;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class GeneralHeadAche extends Illness {
    public GeneralHeadAche() {
        super("Head Ache", 1, false, new ArrayList<>(
                List.of(Symptom.HEAD_ACHE
                ))
        );
    }
}
