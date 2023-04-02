package seedu.pettracker.data;

import org.junit.jupiter.api.Test;
import seedu.pettracker.exceptions.EmptyPetNameException;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class PetListTest {

    @Test
    void addPet_emptyList_noPets() {
        PetList pl = new PetList();
        assertEquals(0, pl.getNumberOfPets());
    }

    void addPetToList() throws EmptyPetNameException {
        PetList.addPet("Bob");
        assertEquals(1, PetList.getNumberOfPets());
    }
}
