package seedu.duke.diagnosis.illnesses.viral;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class GeneralFlu extends Illness {
    public GeneralFlu() {
        super("General Flu", 2, false, new ArrayList<>(
                List.of(Symptom.SNEEZING,
                        Symptom.FATIGUE,
                        Symptom.CHILLS
                ))
        );
    }
}
