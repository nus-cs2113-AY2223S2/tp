package seedu.duke.diagnosis;

import org.junit.jupiter.api.Test;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test for testing of the output of Diagnosis.getPossibleIllnesses. Change the contents of the ArrayList to test
 * for different illnesses.
 * @Author Brennanzuz
 */
public class DiagnosisTest {
    @Test
    public void diagnoseGeneralCold() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(List.of(Symptom.BLOCKED_NOSE,
                Symptom.RUNNY_NOSE,
                Symptom.SNEEZING,
                Symptom.CHILLS,
                Symptom.FATIGUE,
                Symptom.THROAT_IRRITATION));
        ArrayList<IllnessMatch> testIllnesses = Diagnosis.getPossibleIllnesses(testSymptoms);
        assertTrue(testIllnesses.stream()
                .anyMatch(illnessMatch -> illnessMatch.getIllness().getIllnessName().equals("General Cold")
                ));
    }
}
