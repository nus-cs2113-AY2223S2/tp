package seedu.duke.command;

import seedu.duke.Ui;

public class InvalidCommand extends Command {
    public void execute() {
        Ui.printErrorMessage();
    }
}
