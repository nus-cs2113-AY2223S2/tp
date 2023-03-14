package seedu.duke.diagnosis.illnesses.allergic;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class Asthma extends Illness {
    public Asthma() {
        super("Asthma", 3, false, new ArrayList<>(
                List.of(Symptom.HYPERVENTILATION,
                        Symptom.HISTORY_OF_ASTHMA,
                        Symptom.DIFFICULTY_BREATHING
                        ))
        );
    }
}
