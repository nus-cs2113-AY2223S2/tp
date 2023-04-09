package seedu.duke.diagnosis.illnesses.psychological;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class Depression extends Illness {
    public Depression() {
        super("Depression", 2, true, new ArrayList<>(
                List.of(Symptom.HISTORY_OF_DEPRESSION
                ))
        );
    }
}
