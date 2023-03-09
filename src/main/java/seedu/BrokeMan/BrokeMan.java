package seedu.BrokeMan;

import seedu.BrokeMan.command.Command;
import seedu.BrokeMan.command.ExitCommand;
import seedu.BrokeMan.parser.Parser;
import seedu.BrokeMan.ui.Ui;

public class BrokeMan {

    /**
     * Main entry-point for the java.duke.Duke application.
     */

    public static void main(String[] args) {
        new BrokeMan().run();
    }

    public static void run() {
        Ui.showWelcomeMessages();
        runCommandUntilExitCommand();
        Ui.showGoodByeMessages();
    }

    public static void runCommandUntilExitCommand() {
        Command command;

        do {
            String userFullInput = Ui.getUserCommand();
            command = Parser.parseCommand(userFullInput);
            command.execute();
        } while (!ExitCommand.isExit(command));
    }
}
