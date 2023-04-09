//@@author tanyizhe
package seedu.duke.medicine;

import org.junit.jupiter.api.Test;
import seedu.duke.diagnosis.IllnessMatch;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

class MedicineManagerTest {
    @Test
    public void getDosage_basedOnMedicine_expectCorrectDosage() {
        String input = "Aspirin";
        MedicineManager medicineManager = new MedicineManager();
        System.out.println(medicineManager.getMedicineDosages(input));
        assertEquals("1 or 2 pills every 4 to 6 hours", medicineManager.getMedicineDosages(input));
    }
    @Test
    public void getDosage_whenNoMedicine_expectNull() {
        String input = "";
        MedicineManager medicineManager = new MedicineManager();
        System.out.println(medicineManager.getMedicineDosages(input));
        assertNull(medicineManager.getMedicineDosages(input));
    }
    @Test
    public void haveTooFewSymptoms_expectEmpty() {
        MedicineManager medicineManager = new MedicineManager();
        ArrayList<Symptom> symptoms = new ArrayList<>();
        symptoms.add(Symptom.WET_STOOL);
        ArrayList<IllnessMatch> possibleIllnesses = medicineManager.analyseIllness(symptoms);
        assertTrue(possibleIllnesses.isEmpty());
    }
    @Test
    public void getFever_expectCorrectMedicine() {
        MedicineManager medicineManager = new MedicineManager();
        ArrayList<Symptom> symptoms = new ArrayList<>();
        symptoms.add(Symptom.FEVER);
        ArrayList<IllnessMatch> possibleIllnesses = medicineManager.analyseIllness(symptoms);
        ArrayList<String> medicineArrayList = new ArrayList<>();
        for (IllnessMatch illnessMatch : possibleIllnesses) {
            medicineArrayList = medicineManager
                    .getRelevantMedicationInString(illnessMatch.getIllness().getIllnessName());
        }
        assertEquals("Paracetamol", medicineArrayList.get(0));
    }
    @Test
    public void getBronchitis_expectCorrectMedicine() {
        MedicineManager medicineManager = new MedicineManager();
        ArrayList<Symptom> symptoms = new ArrayList<>();
        symptoms.add(Symptom.THROAT_IRRITATION);
        symptoms.add(Symptom.HEAD_ACHE);
        symptoms.add(Symptom.RUNNY_NOSE);
        symptoms.add(Symptom.FATIGUE);
        ArrayList<IllnessMatch> possibleIllnesses = medicineManager.analyseIllness(symptoms);
        ArrayList<String> medicineArrayList = new ArrayList<>();
        medicineArrayList = medicineManager
                .getRelevantMedicationInString(possibleIllnesses.get(0).getIllness().getIllnessName());
        assertEquals("Guaifenesin", medicineArrayList.get(0));
        assertEquals("Ibuprofen", medicineArrayList.get(1));
    }
    @Test
    public void getSoreThroat_expectCorrectMedicine() {
        MedicineManager medicineManager = new MedicineManager();
        ArrayList<Symptom> symptoms = new ArrayList<>();
        symptoms.add(Symptom.THROAT_IRRITATION);
        symptoms.add(Symptom.COUGH_WITH_PHLEGM);
        symptoms.add(Symptom.DRY_COUGH);
        ArrayList<IllnessMatch> possibleIllnesses = medicineManager.analyseIllness(symptoms);
        ArrayList<String> medicineArrayList = new ArrayList<>();
        medicineArrayList = medicineManager
                .getRelevantMedicationInString(possibleIllnesses.get(0).getIllness().getIllnessName());
        assertEquals("Lozenges", medicineArrayList.get(0));
    }
    @Test
    public void getDiarrhoea_expectCorrectMedicine() {
        MedicineManager medicineManager = new MedicineManager();
        ArrayList<Symptom> symptoms = new ArrayList<>();
        symptoms.add(Symptom.WET_STOOL);
        symptoms.add(Symptom.STOMACH_ACHE);
        symptoms.add(Symptom.DIARRHOEA);
        ArrayList<IllnessMatch> possibleIllnesses = medicineManager.analyseIllness(symptoms);
        ArrayList<String> medicineArrayList = new ArrayList<>();
        medicineArrayList = medicineManager
                .getRelevantMedicationInString(possibleIllnesses.get(0).getIllness().getIllnessName());
        assertEquals("Ultracarbon", medicineArrayList.get(0));
        assertEquals("Diphenoxylate", medicineArrayList.get(1));
    }
    @Test
    public void getRashesOrHivesOrPsoriasis_expectNoMedicine() {
        MedicineManager medicineManager = new MedicineManager();
        ArrayList<Symptom> symptoms = new ArrayList<>();
        symptoms.add(Symptom.DRY_SCALING_SKIN);
        symptoms.add(Symptom.RED_SKIN);
        symptoms.add(Symptom.ITCHY_SKIN);
        ArrayList<IllnessMatch> possibleIllnesses = medicineManager.analyseIllness(symptoms);
        ArrayList<String> medicineArrayList = new ArrayList<>();
        medicineArrayList = medicineManager
                .getRelevantMedicationInString(possibleIllnesses.get(0).getIllness().getIllnessName());
        assertNull(medicineArrayList);
    }
}
