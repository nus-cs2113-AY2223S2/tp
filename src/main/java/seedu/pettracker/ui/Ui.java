package seedu.pettracker.ui;

public class Ui {

    public void showWelcomeMessage() {
        System.out.println("Example Welcome Message");
    }

    public void showEndingMessage() {
        System.out.println("Example Ending Message");
    }

    public void exitCommandMessage() {
        System.out.println("Exit Command Received");
    }

    public String getUserInput() {
        return "exit";
    }

}
