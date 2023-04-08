package seedu.moneymind.command;

import seedu.moneymind.ui.Ui;


/**
 * Represents the command to exit the program.
 */
public class ByeCommand implements Command {

    @Override
    public void execute(Ui ui) {
        ui.goodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
