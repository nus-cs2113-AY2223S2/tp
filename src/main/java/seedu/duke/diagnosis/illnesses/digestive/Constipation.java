package seedu.duke.diagnosis.illnesses.digestive;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class Constipation extends Illness {

    public Constipation() {
        super("Constipation", 1, true, new ArrayList<>(
                List.of(Symptom.HARD_LUMPY_STOOL
                ))
        );
    }
}
