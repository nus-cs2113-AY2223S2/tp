package seedu.pettracker.data;
import java.util.ArrayList;

public class PetList {
    public static final String LINE = "____________________";
    private static ArrayList<Pet> petList;
    private static int numberOfPets;

    public PetList() {
        petList = new ArrayList<Pet>();
        this.numberOfPets = 0;
    }

    /**
     * Adds a new pet to the pet list
     *
     * @param petName Name of pet to be added
     */
    public static void addPet(String petName) {
        Pet newPet = new Pet (petName);
        petList.add(newPet);
        numberOfPets += 1;
    }

    public static int find (String petName) {
        for (int i = 0; i < petList.size(); i++) {
            if (petList.get(i).getPetName().equals(petName)) {
                return i;
            }
        }
        return -1;
    }

    public static Pet get (int index) {
        return petList.get(index);
    }

    public static void addStat (String petName, String statName, String statValue) {
        int index = PetList.find(petName);
        petList.get(index).addStat(statName, statValue);
    }

    public static void removeStat (String petName, String statName) {
        int index = PetList.find(petName);
        petList.get(index).removeStat(statName);
    }

    /**
     * Removes all pets with the provided name from the pet list
     *
     * @param petName Name of pet(s) to be removed
     */
    public static void removePet(String petName) {
        int index = PetList.find(petName);
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
}
