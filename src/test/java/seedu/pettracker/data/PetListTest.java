package seedu.pettracker.data;

import org.junit.jupiter.api.Test;
import seedu.pettracker.exceptions.DuplicatePetException;
import seedu.pettracker.exceptions.EmptyPetNameException;
import seedu.pettracker.exceptions.InvalidPetNameException;
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
    void addPetToList() throws EmptyPetNameException, DuplicatePetException, InvalidPetNameException {
        PetList.addPet("Bob");
        assertEquals(1, PetList.getNumberOfPets());
    }

    @Test
    public void addPetToList_duplicateName_exceptionThrown() {
        try {
            PetList.addPet("Alice");
            PetList.addPet("Alice");
            fail();
        } catch (Exception e) {
            assertEquals("ERROR: Pet already exists", e.getMessage());
        }
    }

    @Test
    public void addPetToList_emptyName_exceptionThrown() {
        try {
            PetList.addPet("");
            fail();
        } catch (Exception e) {
            assertEquals("ERROR: Pet Name is empty", e.getMessage());
        }
    }

    @Test
    public void addStatToPet_correctStat_success() throws NonPositiveIntegerException,
            InvalidStatException, PetNotFoundException, DuplicatePetException, EmptyPetNameException,
            InvalidPetNameException {
        PetList.addPet("Candy");
        PetList.addStat("Candy", "Weight", "10");
    }

    @Test
    public void addStatToPet_nonPositiveInteger_exceptionThrown() {
        try {
            PetList.addPet("Dylan");
            PetList.addStat("Dylan", "Weight", "-1");
            fail();
        } catch (Exception e) {
            assertEquals("ERROR: Integer provided should be above 0", e.getMessage());
        }
    }

    @Test
    public void addStatToPet_invalidStat_exceptionThrown() {
        try {
            PetList.addPet("Ellie");
            PetList.addStat("Ellie", "invalid", "10");
            fail();
        } catch (Exception e) {
            assertEquals("ERROR: The only valid stats are type, age, or weight.", e.getMessage());
        }
    }

    @Test
    public void addStatToPet_petNotFound_exceptionThrown() {
        try {
            PetList.addPet("Freddy");
            PetList.addStat("invalid", "Weight", "10");
            fail();
        } catch (Exception e) {
            assertEquals("ERROR: Pet not Found", e.getMessage());
        }
    }

    @Test
    public void editStatToPet_correctStat_success() throws NonPositiveIntegerException,
            InvalidStatException, PetNotFoundException, DuplicatePetException, EmptyPetNameException,
            InvalidPetNameException {
        PetList.addPet("EditStat1");
        PetList.addStat("EditStat1", "Weight", "10");
        PetList.editPetStats("EditStat1", "Weight", "20");
    }

    @Test
    public void editStatToPet_nonPositiveInteger_exceptionThrown() {
        try {
            PetList.addPet("EditStat2");
            PetList.addStat("EditStat2", "Weight", "10");
            PetList.editPetStats("EditStat2", "Weight", "-1");
            fail();
        } catch (Exception e) {
            assertEquals("ERROR: Integer provided should be above 0", e.getMessage());
        }
    }

    @Test
    public void editStatToPet_invalidStat_exceptionThrown() {
        try {
            PetList.addPet("EditStat3");
            PetList.addStat("EditStat3", "Weight", "10");
            PetList.editPetStats("EditStat3", "invalid", "20");
            fail();
        } catch (Exception e) {
            assertEquals("ERROR: The only valid stats are type, age, or weight.", e.getMessage());
        }
    }

    @Test
    public void editStatToPet_petNotFound_exceptionThrown() {
        try {
            PetList.addPet("EditStat4");
            PetList.addStat("EditStat4", "Weight", "10");
            PetList.editPetStats("EditStatInvalid", "Weight", "20");
            fail();
        } catch (Exception e) {
            assertEquals("ERROR: Pet not Found", e.getMessage());
        }
    }
    @Test
    public void removePet_petFound_success() throws PetNotFoundException, DuplicatePetException,
            EmptyPetNameException, InvalidPetNameException {
        PetList.addPet("George");
        PetList.removePet("George");
    }

    @Test
    public void removePet_petNotFound_exceptionThrown() {
        try {
            PetList.removePet("doesnotexist");
        } catch (Exception e) {
            assertEquals("ERROR: Pet not Found", e.getMessage());
        }
    }
}
