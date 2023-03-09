package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.medicine.Medicine;
import static org.junit.jupiter.api.Assertions.*;

class MedicineTest {

    @Test
    void getDosage_basedOnMedicine_expectCorrectDosage() {
        String input = "Paracetamol";
        Medicine paracetamol = new Medicine(input);
        assertEquals("2 pills 2 times a day", paracetamol.getDosage());

    }
}