package seedu.pettracker.ui;

public class Ui {

    public void showWelcomeMessage() {
        System.out.println("Welcome to the Pet Health Tracker!");
    }

    /**
     * Message that prints when the exit command is executed
     */
    public void exitCommandMessage() {
        System.out.println("Goodbye! See you soon.");
    }

    public void addPetCommandMessage(String petName) {
        System.out.println("Successfully added new pet: " + petName);
    }

    public void addStatCommandMessage(String petName, String statName, String statValue) {
        System.out.println("Updated " + statName " to " + statValue + " for " + petName);
    }

    public void invalidCommandMessage() {
        System.out.println("Please enter a valid command!");
    }

    public void removePetCommandMessage(String petName) {
        System.out.println("Successfully removed pet: " + petName);
    }

    public void removeStatCommandMessage(String petName, String statName) {
        System.out.println("Successfully removed " + statName + " from " + petName);
    }


    public String getUserInput() {
        //sample hard coded input to simulate the exit command
        return "exit";
    }

}
