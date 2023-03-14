package seedu.duke.diagnosis.illnesses.bacterial;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class GeneralBacterialInfection extends Illness {

    public GeneralBacterialInfection() {
        super("General Bacterial Infection", 1, false, new ArrayList<>(
                List.of(Symptom.PUSS,
                        Symptom.GENERAL_SWELLING
                ))
        );
    }
}
