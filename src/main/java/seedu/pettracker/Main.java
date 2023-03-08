package seedu.pettracker;

import seedu.pettracker.commands.Command;
import seedu.pettracker.parser.CommandParser;
import seedu.pettracker.ui.Ui;

/**
 * Entry point for the Pet Tracker Application
 * Initializes required classes of the application and begins
 */

public class Main {

    private Ui ui;
    private CommandParser commandParser;

    /**
     * Creates the Main class by initializing the other classes
     */
    public Main() {
        ui = new Ui();
        commandParser = new CommandParser();
    }

    /**
     * Runs the program by showing a welcome message
     * and proceed to run commands until Exit command is received
     */
    public void run() {
        ui.showWelcomeMessage();
        runCommandTillExit();
        ui.showEndingMessage();
        System.exit(0);
    }


    /**
     * Executes commands until isExit is changed to true
     */
    public void runCommandTillExit() {
        boolean isExit = false;
        while (!isExit) {
            Command command;
            String commandString = ui.getUserInput();
            command = commandParser.parseCommand(commandString);
            command.execute(ui);
            isExit = command.isExit();
        }

    }

    public static void main(String[] args) {
        new Main().run();
    }


}
