package seedu.duke.diagnosis;

import org.junit.jupiter.api.Test;
import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.illnesses.viral.GeneralCold;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@Brennanzuz
public class DiagnosisTest {
    @Test
    public void diagnoseGeneralCold() {
        ArrayList<Symptom> testSymptoms = new ArrayList<Symptom>(List.of(Symptom.BLOCKED_NOSE,
                Symptom.RUNNY_NOSE,
                Symptom.SNEEZING,
                Symptom.CHILLS,
                Symptom.FATIGUE,
                Symptom.THROAT_IRRITATION));
        ArrayList<IllnessMatch> testIllnesses = Diagnosis.getPossibleIllnesses(testSymptoms);
        assertTrue(testIllnesses.stream()
                .anyMatch(illnessMatch -> illnessMatch.getIllness().getIllnessName() == "General Cold"
                ));
    }
}
