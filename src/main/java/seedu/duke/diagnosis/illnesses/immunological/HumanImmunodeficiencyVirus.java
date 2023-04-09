package seedu.duke.diagnosis.illnesses.immunological;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class HumanImmunodeficiencyVirus extends Illness {
    public HumanImmunodeficiencyVirus() {
        super("Human Immunodeficiency Virus (HIV)", 3, true, new ArrayList<>(
                List.of(Symptom.CHILLS,
                        Symptom.FATIGUE,
                        Symptom.FEVER,
                        Symptom.THROAT_IRRITATION,
                        Symptom.SWOLLEN_LYMPH_NODES,
                        Symptom.ITCHY_SKIN,
                        Symptom.MUSCLE_ACHE,
                        Symptom.SUSCEPTIBILITY_TO_ILLNESS
                ))
        );
    }
}
