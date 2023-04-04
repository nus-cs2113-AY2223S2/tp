package seedu.duke.symptom;

import org.junit.jupiter.api.Test;
import seedu.duke.diagnosis.symptoms.Symptom;
import seedu.duke.diagnosis.symptoms.SymptomHandler;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit test for testing of the output of SymptomHandler.printSymptoms. Change the contents of the ArrayList to test
 * for different symptoms.
 */
public class SymptomTest {
    @Test
    public void printSymptoms() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(List.of(
                Symptom.FATIGUE,
                Symptom.RUNNY_NOSE,
                Symptom.SNEEZING,
                Symptom.CHILLS,
                Symptom.FATIGUE,
                Symptom.THROAT_IRRITATION));
        ArrayList<String> testSymptomNames = new ArrayList<>(List.of(
                "fatigue",
                "runny nose",
                "persistent sneezing",
                "chills",
                "fatigue",
                "throat irritation"));
        for (int i = 0; i < testSymptomNames.size(); i += 1) {
            assertEquals(SymptomHandler.toString(testSymptoms.get(i)), testSymptomNames.get(i));
        }
    }
}
