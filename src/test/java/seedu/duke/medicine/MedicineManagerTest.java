package seedu.duke.medicine;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;



class MedicineManagerTest {
    @Test
    public void getDosage_basedOnMedicine_expectCorrectDosage() {
        String input = "Aspirin";
        MedicineManager medicineManager = new MedicineManager();
        System.out.println(medicineManager.getMedicineDosages(input));
        assertEquals("1 or 2 pills every 4 to 6 hours", medicineManager.getMedicineDosages(input));
    }
}
