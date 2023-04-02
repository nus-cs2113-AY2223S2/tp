package seedu.pettracker.data;

import org.junit.jupiter.api.Test;
import seedu.pettracker.exceptions.NonPositiveIntegerException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetTest {
    @Test
    public void testStringConversion() throws NumberFormatException, NonPositiveIntegerException {
        Pet pet = new Pet("Dolly");
        pet.setPetType("Dog");
        pet.setAge("3");
        pet.setWeight("10");
        assertEquals("Dolly\nType: Dog\nAge: 3\nWeight: 10", pet.toString());
    }
}
