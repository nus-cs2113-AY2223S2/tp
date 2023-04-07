package seedu.pettracker.data;

import seedu.pettracker.exceptions.DuplicatePetException;
import seedu.pettracker.exceptions.EmptyPetNameException;
import seedu.pettracker.exceptions.InvalidPetNameException;
import seedu.pettracker.exceptions.InvalidStatException;
import seedu.pettracker.exceptions.NonPositiveIntegerException;
import seedu.pettracker.exceptions.PetNotFoundException;
import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;

import java.util.ArrayList;

public class PetList {
    public static final String LINE = "____________________";
    private static ArrayList<Pet> petList = new ArrayList<>();
    private static int numberOfPets;

    public PetList() {
        numberOfPets = 0;
    }

    /**
     * Adds a new pet to the pet list
     *
     * @param petName Name of pet to be added
     */
    public static void addPet(String petName) throws EmptyPetNameException, DuplicatePetException,
            InvalidPetNameException {
        if (petName.trim().isEmpty()) {
            throw new EmptyPetNameException();
        }

        if(petName.trim().contains("|")) {
            throw new InvalidPetNameException();
        }

        int index = PetList.find(petName);
        if (index != -1) {
            throw new DuplicatePetException();
        }
        Pet newPet = new Pet(petName);
        petList.add(newPet);
        numberOfPets += 1;
    }

    private static int find(String petName) {
        for (int i = 0; i < petList.size(); i++) {
            if (petList.get(i).getPetName().equals(petName)) {
                return i;
            }
        }
        return -1;
    }

    private static Pet get(int index) {
        return petList.get(index);
    }

    /**
     * Retrieves a pet in the PetList to add a stat to
     *
     * @param petName   Name of pet to edit
     * @param statName  Name of stat to add
     * @param statValue New stat Value
     * @throws NumberFormatException       When stat is Age/Weight and is not a number
     * @throws NonPositiveIntegerException When stat is Age/Weight and is non-positive
     * @throws InvalidStatException        When stat is not Type/Age/Weight
     * @throws PetNotFoundException        When Pet is not in PetList
     */
    public static void addStat(String petName, String statName, String statValue)
            throws NumberFormatException, NonPositiveIntegerException, InvalidStatException, PetNotFoundException {
        int index = PetList.find(petName);
        if (index == -1) {
            throw new PetNotFoundException();
        } else {
            petList.get(index).addStat(statName, statValue);
        }
    }

    /**
     * Retrieves a pet in the PetList and remove a stat
     *
     * @param petName  Name of pet to remove from
     * @param statName Name of stat to remove
     * @throws InvalidStatException When the stat does not exist
     * @throws PetNotFoundException When Pet is not in PetList
     */
    public static void removeStat(String petName, String statName) throws InvalidStatException, PetNotFoundException {
        int index = PetList.find(petName);
        if (index == -1) {
            throw new PetNotFoundException();
        }
        petList.get(index).removeStat(statName);
    }

    /**
     * Removes pet with the provided name from the pet list
     *
     * @param petName Name of pet to be removed
     * @throws PetNotFoundException When Pet is not in PetList
     */
    public static void removePet(String petName) throws PetNotFoundException {
        int index = PetList.find(petName);
        if (index == -1) {
            throw new PetNotFoundException();
        }
        assert (index >= 0) : "pet not in list";
        petList.remove(index);
        numberOfPets -= 1;
    }

    /**
     * Lists all pets in the current list, as well as the number of pets
     */
    public static void list() {
        for (Pet pet : petList) {
            System.out.println("Name: " + pet.getPetName());
            System.out.println("Type: " + pet.getPetType());
            System.out.println("Age: " + pet.getAge());
            System.out.println("Weight: " + pet.getWeight());
            System.out.println(LINE);
        }
        System.out.println("Number of pets: " + numberOfPets);
    }

    public static int getNumberOfPets() {
        return numberOfPets;
    }

    /**
     * Edit pet stats of a pet in the PetList to change the previous value to a new value
     *
     * @param petName  Name of pet to edit
     * @param stat     Name of stat to edit
     * @param newValue New stat Value
     * @throws NonPositiveIntegerException When stat is Age/Weight and is non-positive
     * @throws NumberFormatException       When stat is Age/Weight and is not a number
     * @throws InvalidStatException        When stat is not Type/Age/Weight
     * @throws PetNotFoundException        When Pet is not in PetList
     */
    public static void editPetStats(String petName, String stat, String newValue)
            throws NonPositiveIntegerException, NumberFormatException, InvalidStatException, PetNotFoundException {
        int index = PetList.find(petName);
        if (index == -1) {
            throw new PetNotFoundException();
        }
        Pet petToEdit = PetList.get(index);
        switch (stat.toLowerCase()) {
        case "name":
            petToEdit.setPetName(newValue);
            break;
        case "type":
            petToEdit.setPetType(newValue);
            break;
        case "age":
            petToEdit.setAge(newValue);
            break;
        case "weight":
            petToEdit.setWeight(newValue);
            break;
        default:
            throw new InvalidStatException();
        }
    }

    /**
     * Saves the current PetList to the output file
     *
     * @param storage Storage to save file to
     * @param ui      Ui for any prints
     */
    public static void savePetsToStorage(Storage storage, Ui ui) {
        storage.savePets(petList, ui);
    }
}
