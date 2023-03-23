package seedu.duke.diagnosis;

import org.junit.jupiter.api.Test;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test for testing of the output of Diagnosis.getPossibleIllnesses. Change the contents of the ArrayList to test
 * for different illnesses.
 */
public class DiagnosisTest {
    //@@BrennanZuz
    @Test
    public void diagnoseGeneralCold() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(List.of(Symptom.FATIGUE,
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

    //@@JeraldChen
    @Test
    public void diagnoseAsthma() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(List.of(Symptom.HYPERVENTILATION,
                Symptom.HISTORY_OF_ASTHMA,
                Symptom.DIFFICULTY_BREATHING));
        ArrayList<IllnessMatch> testIllnesses = Diagnosis.getPossibleIllnesses(testSymptoms);
        assertTrue(testIllnesses.stream()
                .anyMatch(illnessMatch -> illnessMatch.getIllness().getIllnessName().equals("Asthma")
                ));
    }

    //@@JeraldChen
    @Test
    public void diagnoseGeneralAllergicReaction() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(List.of(Symptom.HISTORY_OF_ALLERGIES));
        ArrayList<IllnessMatch> testIllnesses = Diagnosis.getPossibleIllnesses(testSymptoms);
        assertTrue(testIllnesses.stream()
                .anyMatch(illnessMatch -> illnessMatch.getIllness().getIllnessName().equals("General Allergic Reaction")
                ));
    }

    //@@JeraldChen
    @Test
    public void diagnoseBronchitis() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(List.of(Symptom.THROAT_IRRITATION,
                Symptom.HEAD_ACHE,
                Symptom.RUNNY_NOSE,
                Symptom.FATIGUE));
        ArrayList<IllnessMatch> testIllnesses = Diagnosis.getPossibleIllnesses(testSymptoms);
        assertTrue(testIllnesses.stream()
                .anyMatch(illnessMatch -> illnessMatch.getIllness().getIllnessName().equals("Bronchitis")
                ));
    }

    //@@JeraldChen
    @Test
    public void diagnoseGeneralBacterialInfection() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(List.of(Symptom.PUSS,
                Symptom.GENERAL_SWELLING
                ));
        ArrayList<IllnessMatch> testIllnesses = Diagnosis.getPossibleIllnesses(testSymptoms);
        assertTrue(testIllnesses.stream()
                .anyMatch(illnessMatch -> illnessMatch.getIllness().getIllnessName().equals("General Bacterial Infection")
                ));
    }

    //@@JeraldChen
    @Test
    public void diagnosePneumonia() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(List.of(Symptom.CHEST_PAIN,
                Symptom.COUGH_WITH_PHLEGM,
                Symptom.CHILLS,
                Symptom.FEVER,
                Symptom.FATIGUE,
                Symptom.BREATHLESSNESS,
                Symptom.NAUSEA,
                Symptom.SNIFFING
        ));
        ArrayList<IllnessMatch> testIllnesses = Diagnosis.getPossibleIllnesses(testSymptoms);
        assertTrue(testIllnesses.stream()
                .anyMatch(illnessMatch -> illnessMatch.getIllness().getIllnessName().equals("Pneumonia")
                ));

    }

    //@@JeraldChen
    @Test
    public void diagnoseSoreThroat() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(
                List.of(Symptom.THROAT_IRRITATION,
                        Symptom.COUGH_WITH_PHLEGM,
                        Symptom.DRY_COUGH
        ));
        ArrayList<IllnessMatch> testIllnesses = Diagnosis.getPossibleIllnesses(testSymptoms);
        assertTrue(testIllnesses.stream()
                .anyMatch(illnessMatch -> illnessMatch.getIllness().getIllnessName().equals("Sore Throat")
                ));
    }

    //@@JeraldChen
    @Test
    public void diagnoseTuberculosis() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(
                List.of(Symptom.DRY_COUGH,
                        Symptom.COUGH_WITH_PHLEGM,
                        Symptom.BLEEDING_WHEN_COUGHING,
                        Symptom.CHEST_PAIN
                ));
        ArrayList<IllnessMatch> testIllnesses = Diagnosis.getPossibleIllnesses(testSymptoms);
        assertTrue(testIllnesses.stream()
                .anyMatch(illnessMatch -> illnessMatch.getIllness().getIllnessName().equals("Tuberculosis")
                ));
    }

    //@@JeraldChen
    @Test
    public void diagnoseCoronaryHeartDisease() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(
                List.of(Symptom.HIGH_HEART_RATE,
                        Symptom.CHEST_PAIN,
                        Symptom.BREATHLESSNESS,
                        Symptom.NAUSEA,
                        Symptom.CHILLS
                ));
        ArrayList<IllnessMatch> testIllnesses = Diagnosis.getPossibleIllnesses(testSymptoms);
        assertTrue(testIllnesses.stream()
                .anyMatch(illnessMatch -> illnessMatch.getIllness().getIllnessName().equals("Coronary Heart Disease")
                ));
    }

    //@@JeraldChen
    @Test
    public void diagnoseHypertension() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(
                List.of(Symptom.HIGH_HEART_RATE,
                        Symptom.HEAD_ACHE,
                        Symptom.BLURRED_VISION,
                        Symptom.SENSITIVITY_TO_LIGHT_AND_SOUND,
                        Symptom.FEVER
                ));
        ArrayList<IllnessMatch> testIllnesses = Diagnosis.getPossibleIllnesses(testSymptoms);
        assertTrue(testIllnesses.stream()
                .anyMatch(illnessMatch -> illnessMatch.getIllness().getIllnessName().equals("Hypertension")
                ));
    }

}
