package seedu.duke.diagnosis.illnesses.digestive;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class Hemorrhoids extends Illness {
    public Hemorrhoids() {
        super("Hemorrhoids", 2, true, new ArrayList<>(
                List.of(Symptom.BLOODIED_STOOLS
                ))
        );
    }
}
