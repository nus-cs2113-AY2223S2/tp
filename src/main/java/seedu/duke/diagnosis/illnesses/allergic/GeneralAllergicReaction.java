package seedu.duke.diagnosis.illnesses.allergic;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class GeneralAllergicReaction extends Illness {
    public GeneralAllergicReaction() {
        super("General Allergic Reaction", 3, false, new ArrayList<>(
                List.of(Symptom.HISTORY_OF_ALLERGIES
                ))
        );
    }
}
