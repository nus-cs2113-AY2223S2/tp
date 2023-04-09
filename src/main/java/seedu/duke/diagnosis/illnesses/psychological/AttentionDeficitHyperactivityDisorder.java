package seedu.duke.diagnosis.illnesses.psychological;

import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

//@@author Brennanzuz
public class AttentionDeficitHyperactivityDisorder extends Illness {
    public AttentionDeficitHyperactivityDisorder() {
        super("Attention Deficit Hyperactivity Disorder (ADHD)", 1, true, new ArrayList<>(
                List.of(Symptom.HISTORY_OF_ADHD
                ))
        );
    }
}
