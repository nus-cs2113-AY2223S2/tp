package seedu.duke.diagnosis.illnesses.viral;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class Dengue extends Illness {
    public Dengue() {
        super("Dengue Fever", 3, false, new ArrayList<>(
                List.of(Symptom.FEVER,
                        Symptom.CHILLS,
                        Symptom.GENERAL_PAIN,
                        Symptom.HEAD_ACHE,
                        Symptom.VOMITING
                ))
        );
    }
}
