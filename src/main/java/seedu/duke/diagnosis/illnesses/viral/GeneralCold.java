package seedu.duke.diagnosis.illnesses.viral;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class GeneralCold extends Illness {

    public GeneralCold() {
        super("General Cold", 2, true, new ArrayList<>(
                List.of(Symptom.FATIGUE,
                        Symptom.RUNNY_NOSE,
                        Symptom.SNEEZING,
                        Symptom.CHILLS,
                        Symptom.FATIGUE,
                        Symptom.THROAT_IRRITATION
                ))
        );
    }
}
