package seedu.pettracker.data;

import org.junit.jupiter.api.Test;
import seedu.pettracker.exceptions.DuplicatePetException;
import seedu.pettracker.exceptions.EmptyPetNameException;
import seedu.pettracker.exceptions.InvalidStatException;
import seedu.pettracker.exceptions.NonPositiveIntegerException;
import seedu.pettracker.exceptions.PetNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PetListTest {

    @Test
    void addPet_emptyList_noPets() {
        PetList pl = new PetList();
        assertEquals(0, pl.getNumberOfPets());
    }

    @Test
    void addPetToList() throws EmptyPetNameException, DuplicatePetException {
        PetList.addPet("Bob");
        assertEquals(1, PetList.getNumberOfPets());
    }

    @Test
    public void addPetToList_duplicateName_exceptionThrown() {
        try {
            PetList.addPet("Alice");
            PetList.addPet("Alice");
            fail();
        } catch (Exception e){
            assertEquals("ERROR: Pet already exists",e.getMessage());
        }
    }

    @Test
    public void addPetToList_emptyName_exceptionThrown() {
        try {
            PetList.addPet("");
            fail();
        } catch (Exception e){
            assertEquals("ERROR: Pet Name is empty",e.getMessage());
        }
    }

    @Test
    public void addStatToPet_correctStat_success() throws NonPositiveIntegerException,
            InvalidStatException, PetNotFoundException, DuplicatePetException, EmptyPetNameException {
        PetList.addPet("Candy");
        PetList.addStat("Candy","Weight","10");
    }

    @Test
    public void addStatToPet_nonPositiveInteger_exceptionThrown() {
        try {
            PetList.addPet("Dylan");
            PetList.addStat("Dylan","Weight","-1");
            fail();
        } catch (Exception e){
            assertEquals("ERROR: Integer provided should be above 0",e.getMessage());
        }
    }

    @Test
    public void addStatToPet_invalidStat_exceptionThrown() {
        try {
            PetList.addPet("Ellie");
            PetList.addStat("Ellie","invalid","10");
            fail();
        } catch (Exception e){
            assertEquals("ERROR: The only valid stats are type, age, or weight.",e.getMessage());
        }
    }

    @Test
    public void addStatToPet_petNotFound_exceptionThrown() {
        try {
            PetList.addPet("Freddy");
            PetList.addStat("invalid","Weight","10");
            fail();
        } catch (Exception e){
            assertEquals("ERROR: Pet not Found",e.getMessage());
        }
    }
}
