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
        ArrayList<Symptom> testSymptoms = new ArrayList<>(List.of(
                Symptom.FATIGUE,
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

    //@@author JeraldChen
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

    //@@author JeraldChen
    @Test
    public void diagnoseGeneralAllergicReaction() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(List.of(Symptom.HISTORY_OF_ALLERGIES));
        ArrayList<IllnessMatch> testIllnesses = Diagnosis.getPossibleIllnesses(testSymptoms);
        assertTrue(testIllnesses.stream()
                .anyMatch(illnessMatch -> illnessMatch.getIllness().getIllnessName().equals("General Allergic Reaction")
                ));
    }

    //@@author JeraldChen
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

    //@@author JeraldChen
    @Test
    public void diagnoseGeneralBacterialInfection() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(List.of(Symptom.PUSS,
                Symptom.GENERAL_SWELLING
                ));
        ArrayList<IllnessMatch> testIllnesses = Diagnosis.getPossibleIllnesses(testSymptoms);
        assertTrue(testIllnesses.stream()
                .anyMatch(illnessMatch ->
                        illnessMatch.getIllness().getIllnessName().equals("General Bacterial Infection")
                ));
    }

    //@@author JeraldChen
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

    //@@author JeraldChen
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

    //@@author JeraldChen
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

    //@@author JeraldChen
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

    //@@author JeraldChen
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
                .anyMatch(illnessMatch ->
                        illnessMatch.getIllness().getIllnessName().equals("Hypertension (High Blood Pressure)")
                ));
    }

    //@@author JeraldChen
    @Test
    public void diagnoseGeneralToothAche() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(
                List.of(Symptom.TOOTH_ACHE
                ));
        ArrayList<IllnessMatch> testIllnesses = Diagnosis.getPossibleIllnesses(testSymptoms);
        assertTrue(testIllnesses.stream()
                .anyMatch(illnessMatch ->
                        illnessMatch.getIllness().getIllnessName().equals("Tooth Ache")
                ));
    }

    //@@author JeraldChen
    @Test
    public void diagnoseToothDecay() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(
                List.of(Symptom.TOOTH_ACHE,
                        Symptom.BLACK_TEETH
                ));
        ArrayList<IllnessMatch> testIllnesses = Diagnosis.getPossibleIllnesses(testSymptoms);
        assertTrue(testIllnesses.stream()
                .anyMatch(illnessMatch ->
                        illnessMatch.getIllness().getIllnessName().equals("Tooth Decay")
                ));
    }

    //@@author JeraldChen
    @Test
    public void diagnoseGeneralRash() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(
                List.of(Symptom.ITCHY_SKIN,
                        Symptom.RED_SKIN
                ));
        ArrayList<IllnessMatch> testIllnesses = Diagnosis.getPossibleIllnesses(testSymptoms);
        assertTrue(testIllnesses.stream()
                .anyMatch(illnessMatch ->
                        illnessMatch.getIllness().getIllnessName().equals("Rashes")
                ));
    }

    //@@author JeraldChen
    @Test
    public void diagnoseHives() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(
                List.of(Symptom.ITCHY_EYE,
                        Symptom.RED_SKIN,
                        Symptom.SWELLING_SKIN
                ));
        ArrayList<IllnessMatch> testIllnesses = Diagnosis.getPossibleIllnesses(testSymptoms);
        assertTrue(testIllnesses.stream()
                .anyMatch(illnessMatch ->
                        illnessMatch.getIllness().getIllnessName().equals("Hives")
                ));
    }

    //@@author JeraldChen
    @Test
    public void diagnosePsoriasis() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(
                List.of(Symptom.ITCHY_SKIN,
                        Symptom.RED_SKIN,
                        Symptom.DRY_SCALING_SKIN
                ));
        ArrayList<IllnessMatch> testIllnesses = Diagnosis.getPossibleIllnesses(testSymptoms);
        assertTrue(testIllnesses.stream()
                .anyMatch(illnessMatch ->
                        illnessMatch.getIllness().getIllnessName().equals("Psoriasis")
                ));
    }

    //@@author JeraldChen
    @Test
    public void diagnoseConstipation() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(
                List.of(Symptom.HARD_LUMPY_STOOL
                ));
        ArrayList<IllnessMatch> testIllnesses = Diagnosis.getPossibleIllnesses(testSymptoms);
        assertTrue(testIllnesses.stream()
                .anyMatch(illnessMatch ->
                        illnessMatch.getIllness().getIllnessName().equals("Constipation")
                ));
    }

    //@@author JeraldChen
    @Test
    public void diagnoseDiarrhoea() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(
                List.of(Symptom.WET_STOOL,
                        Symptom.STOMACH_ACHE,
                        Symptom.DIARRHOEA
                ));
        ArrayList<IllnessMatch> testIllnesses = Diagnosis.getPossibleIllnesses(testSymptoms);
        assertTrue(testIllnesses.stream()
                .anyMatch(illnessMatch ->
                        illnessMatch.getIllness().getIllnessName().equals("Diarrhoea")
                ));
    }

    //@@author JeraldChen
    @Test
    public void diagnoseFoodPoisoning() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(
                List.of(Symptom.DIARRHOEA,
                        Symptom.STOMACH_ACHE,
                        Symptom.NAUSEA,
                        Symptom.VOMITING,
                        Symptom.FEVER
                ));
        ArrayList<IllnessMatch> testIllnesses = Diagnosis.getPossibleIllnesses(testSymptoms);
        assertTrue(testIllnesses.stream()
                .anyMatch(illnessMatch ->
                        illnessMatch.getIllness().getIllnessName().equals("Food Poisoning")
                ));
    }

    //@@author JeraldChen
    @Test
    public void diagnoseGeneralStomachAche() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(
                List.of(Symptom.STOMACH_ACHE
                ));
        ArrayList<IllnessMatch> testIllnesses = Diagnosis.getPossibleIllnesses(testSymptoms);
        assertTrue(testIllnesses.stream()
                .anyMatch(illnessMatch ->
                        illnessMatch.getIllness().getIllnessName().equals("Stomach Ache")
                ));
    }

    //@@author JeraldChen
    @Test
    public void diagnoseHemorrhoids() {
        ArrayList<Symptom> testSymptoms = new ArrayList<>(
                List.of(Symptom.BLOODIED_STOOLS
                ));
        ArrayList<IllnessMatch> testIllnesses = Diagnosis.getPossibleIllnesses(testSymptoms);
        assertTrue(testIllnesses.stream()
                .anyMatch(illnessMatch ->
                        illnessMatch.getIllness().getIllnessName().equals("Hemorrhoids")
                ));
    }
}
