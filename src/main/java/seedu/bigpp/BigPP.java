package seedu.bigpp;

import seedu.bigpp.command.Command;
import seedu.bigpp.command.commoncommand.ByeCommand;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.parser.Parser;
import seedu.bigpp.ui.UI;

public class BigPP {

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new BigPP().run();
    }

    public void run() {
        DataStorage.loadAll();
        UI.showWelcome();
        UI.updateUI(true);
        runLoopUntilExit();
    }

    /**
     * Runs the program until the user issues the exit command.
     */
    private void runLoopUntilExit() {
        Command command;
        do {
            String userInput = UI.getInput();
            command = new Parser().parseCommand(userInput);
            String result = "";
            try {
                result = command.executeCommand();
            } catch (PPException e) {
                result = e.getMessage();
            }
            UI.updateUI(false);
            UI.showResult(result);
        } while (!(command instanceof ByeCommand));
    }

}
