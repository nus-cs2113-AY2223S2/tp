package seedu.duke.diagnosis.illnesses.viral;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class Coronavirus extends Illness {
    public Coronavirus() {
        super("COVID-19", 3, false, new ArrayList<>(
                List.of(Symptom.FEVER,
                        Symptom.CHILLS,
                        Symptom.LOSS_OF_TASTE_OR_SMELL,
                        Symptom.THROAT_IRRITATION,
                        Symptom.SNEEZING
                ))
        );
    }
}
