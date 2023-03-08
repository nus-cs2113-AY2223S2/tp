package seedu.pettracker.ui;

public class Ui {

    public void showWelcomeMessage() {
        System.out.println("Example Welcome Message");
    }

    public void showEndingMessage() {
        System.out.println("Example Ending Message");
    }

    /**
     * Message that prints when the exit command is executed
     */
    public void exitCommandMessage() {
        System.out.println("Exit Command Received");
    }

    public String getUserInput() {
        //sample hard coded input to simulate the exit command
        return "exit";
    }

}
