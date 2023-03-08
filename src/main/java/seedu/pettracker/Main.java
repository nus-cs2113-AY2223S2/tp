package seedu.pettracker;

import seedu.pettracker.commands.Command;
import seedu.pettracker.parser.CommandParser;
import seedu.pettracker.ui.Ui;
import java.util.Scanner;

/**
 * Entry point for the Pet Tracker Application
 * Initializes required classes of the application and begins
 */

public class Main {

    private final Ui ui;
    private final CommandParser commandParser;

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
        Scanner in = new Scanner(System.in);
        while (!isExit) {
            String commandString = in.nextLine();
            //String commandString = ui.getUserInput();
            Command c = commandParser.parseCommand(commandString);
            c.execute(ui);
            isExit = c.isExit();
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
