package seedu.pettracker.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void setPetAge_actualInteger_success() throws NumberFormatException, NonPositiveIntegerException {
        Pet pet = new Pet("Alice");
        pet.setAge("10");
    }

    @Test
    public void setPetAge_nonInteger_exceptionThrown() {
        Pet pet = new Pet("Alice");
        assertThrows(NumberFormatException.class, () -> pet.setAge("invalid"),
                "NumberFormatException was expected");
    }

    @Test
    public void setPetAge_invalidInteger_exceptionThrown() {
        Pet pet = new Pet("Alice");
        assertThrows(NonPositiveIntegerException.class, () -> pet.setAge("-1"),
                "NonPositiveIntegerException was expected");
    }

    @Test
    public void setPetWeight_actualInteger_success() throws NumberFormatException, NonPositiveIntegerException {
        Pet pet = new Pet("Alice");
        pet.setWeight("10");
    }

    @Test
    public void setPetWeight_nonInteger_exceptionThrown() {
        Pet pet = new Pet("Alice");
        assertThrows(NumberFormatException.class, () -> pet.setWeight("invalid"),
                "NumberFormatException was expected");
    }

    @Test
    public void setPetWeight_invalidInteger_exceptionThrown() {
        Pet pet = new Pet("Alice");
        assertThrows(NonPositiveIntegerException.class, () -> pet.setWeight("-1"),
                "NonPositiveIntegerException was expected");
    }
}
