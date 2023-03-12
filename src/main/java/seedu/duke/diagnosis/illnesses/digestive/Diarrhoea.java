package seedu.duke.diagnosis.illnesses.digestive;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class Diarrhoea extends Illness {
    public Diarrhoea() {
        super("Diarrhoea", 2, false, new ArrayList<>(
                List.of(Symptom.WET_STOOL,
                        Symptom.STOMACH_ACHE,
                        Symptom.DIARRHOEA
                ))
        );
    }
}
