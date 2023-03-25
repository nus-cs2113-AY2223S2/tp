package seedu.pettracker.ui;

import java.util.Scanner;

/**
 * Class to handle all output from commands and get user input from Standard Input.
 */
public class Ui {

    Scanner in;

    /**
     * Constructor to initialize Scanner object.
     */
    public Ui () {
        in = new Scanner(System.in);
    }

    /**
     * Message that welcomes the user when program loads.
     */
    public void showWelcomeMessage() {
        System.out.println("Welcome to the Pet Health Tracker!");
    }

    /**
     * Message that prints when the program exits and closes the Scanner.
     */
    public void showEndingMessage() {
        in.close();
        System.out.println("Goodbye! See you soon.");
    }

    /**
     * Message that prints when a new pet is added.
     * @param petName String containing the name of the added pet.
     */
    public void addPetCommandMessage(String petName) {
        System.out.println("Successfully added new pet: " + petName);
    }

    /**
     * Message that prints when a pet's stats are edited.
     * @param petName String containing the name of the added pet.
     */
    public void editStatCommandMessage(String petName, String stat, String statValue) {
        System.out.println("Successfully updated " + petName + "'s " + stat + " to " + statValue);
    }

    /**
     * Message that prints when a new stat is added for a pet.
     * @param petName String containing the name of the corresponding pet.
     * @param statName String indicating the name of the added stat.
     * @param statValue String indicating the value for that stat.
     */
    public void addStatCommandMessage(String petName, String statName, String statValue) {
        System.out.println("Updated " + statName + " to " + statValue + " for " + petName);
    }

    /**
     * Message that prints when an invalid command is passed to the program.
     */
    public void invalidCommandMessage() {
        System.out.println("Please enter a valid command!");
    }

    /** 
     * Message that prints when a pet is removed.
     * @param petName String representing the name of the removed pet.
     */
    public void removePetCommandMessage(String petName) {
        System.out.println("Successfully removed pet: " + petName);
    }

    /** 
     * Message that prints when a stat is removed from a pet.
     * @param petName String containing name of the corresponding pet.
     * @param statName String containing name of the stat that was removed.
     */
    public void removeStatCommandMessage(String petName, String statName) {
        System.out.println("Successfully removed " + statName + " from " + petName);
    }


    /** 
     * Gets input from the user from Standard Input.
     * @return String containing the input from the user.
     */
    public String getUserInput() {
        System.out.println();
        return in.nextLine();
    }

    /**
     * Message that prints when a task is added to the task list.
     */
    public void addTodoCommandMessage() {
        System.out.println("Added new task to list");
    }

    /**
     * Message that prints when a task is removed from the task list.
     * @param taskNumber Integer representing the task number of the removed task.
     */
    public void removeTaskCommandMessage(int taskNumber) {
        System.out.println("Successfully removed task " + taskNumber);
    }

    public void editTaskCommandMessage(int taskNumber, String newDescription) {
        System.out.println("Updated task " + taskNumber + " to " + newDescription + ".");
    }

    /**
     * Message that prints before listing all tasks.
     */
    public void listTasksCommandMessage() {
        System.out.println("Here are your tasks:");
    }

    /**
     * Message that prints when a task is marked as done.
     */
    public void markTaskCommandMessage() {
        System.out.println("Task marked as done");
    }

    /**
     * Message that prints when a task is marked as not done.
     */
    public void unmarkTaskCommandMessage() {
        System.out.println("Task marked as not done");
    }

    /**
     * Message that prints when the storage experiences IO error.
     */
    public void fileIOErrorMessage() {
        System.out.println("Error with File IO");
    }

}
