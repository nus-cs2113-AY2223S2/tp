package seedu.pettracker.data;
import java.util.ArrayList;

public class PetList {
    public static final String LINE = "____________________";
    private ArrayList<Pet> petList;
    private int numberOfPets;

    public PetList() {
        this.numberOfPets = 0;
    }

    /**
     * Adds a new pet to the pet list
     *
     * @param petName Name of pet to be added
     */
    public void addPet(String petName) {
        petList.add(new Pet(petName));
        numberOfPets += 1;
    }

    /**
     * Removes all pets with the provided name from the pet list
     *
     * @param petName Name of pet(s) to be removed
     */
    public void removePet(String petName) {
        for (Pet pet : petList) {
            if (pet.getPetName().equals(petName)) {
                petList.remove(pet);
                numberOfPets -= 1;
            }
        }
    }

    /**
     * Lists all pets in the current list, as well as the number of pets
     */
    public void list() {
        for (Pet pet : petList) {
            System.out.println("Name: " + pet.getPetName());
            System.out.println("Type: " + pet.getPetType());
            System.out.println("Age: " + pet.getAge());
            System.out.println("Weight: " + pet.getWeight());
            System.out.println(LINE);
        }
        System.out.println("Number of pets: " + numberOfPets);
    }

    public int getNumberOfPets() {
        return numberOfPets;
    }
}
