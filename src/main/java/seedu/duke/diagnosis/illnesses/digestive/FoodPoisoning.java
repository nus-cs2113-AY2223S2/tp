package seedu.duke.diagnosis.illnesses.digestive;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class FoodPoisoning extends Illness {
    public FoodPoisoning() {
        super("Food Poisoning", 3, false, new ArrayList<>(
                List.of(Symptom.DIARRHOEA,
                        Symptom.STOMACH_ACHE,
                        Symptom.NAUSEA,
                        Symptom.VOMITING,
                        Symptom.FEVER
                ))
        );
    }
}
