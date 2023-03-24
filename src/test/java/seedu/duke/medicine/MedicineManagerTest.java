package seedu.duke.medicine;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import seedu.duke.diagnosis.IllnessMatch;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;


class MedicineManagerTest {
    @Test
    public void getDosage_basedOnMedicine_expectCorrectDosage() {
        String input = "Aspirin";
        MedicineManager medicineManager = new MedicineManager();
        System.out.println(medicineManager.getMedicineDosages(input));
        assertEquals("1 or 2 pills every 4 to 6 hours", medicineManager.getMedicineDosages(input));
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
}
