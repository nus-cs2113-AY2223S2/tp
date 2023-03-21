package seedu.duke.diagnosis.illnesses.psychological;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class Insomnia extends Illness {
    public Insomnia() {
        super("Insomnia", 1, false, new ArrayList<>(
                List.of(Symptom.SLEEPLESSNESS
                ))
        );
    }
}
