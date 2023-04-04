package seedu.pettracker.data;

import org.junit.jupiter.api.Test;
import seedu.pettracker.exceptions.DuplicatePetException;
import seedu.pettracker.exceptions.EmptyPetNameException;

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
}
