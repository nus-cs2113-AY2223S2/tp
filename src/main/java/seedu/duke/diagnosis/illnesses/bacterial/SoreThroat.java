package seedu.duke.diagnosis.illnesses.bacterial;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brennanzuz
 */
public class SoreThroat extends Illness {
    public SoreThroat() {
        super("Sore Throat", 1, true, new ArrayList<>(
                List.of(Symptom.THROAT_IRRITATION,
                        Symptom.COUGH_WITH_PHLEGM,
                        Symptom.DRY_COUGH
                ))
        );
    }
}
