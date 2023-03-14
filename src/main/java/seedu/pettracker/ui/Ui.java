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

    public String getUserInput() {
        //sample hard coded input to simulate the exit command
        return "exit";
    }

}
