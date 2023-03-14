package seedu.duke.diagnosis.illnesses.bacterial;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class Pneumonia extends Illness {
    public Pneumonia() {
        super("Pneumonia", 2, true, new ArrayList<>(
                List.of(Symptom.CHEST_PAIN,
                        Symptom.COUGH_WITH_PHLEGM,
                        Symptom.CHILLS,
                        Symptom.FEVER,
                        Symptom.FATIGUE,
                        Symptom.BREATHLESSNESS,
                        Symptom.NAUSEA,
                        Symptom.SNIFFING
                ))
        );
    }
}
