package seedu.pettracker.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class PetListTest {

    @Test
    void addPet_emptyList_noPets() {
        PetList pl = new PetList();
        assertEquals(0, pl.getNumberOfPets());
    }
}
