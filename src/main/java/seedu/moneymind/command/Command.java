package seedu.moneymind.command;

import seedu.moneymind.ui.Ui;

/**
 * Represents a command interface with hidden internal logic and the ability to be executed.
 */
public interface Command {

    void execute(Ui ui);

    boolean isExit();

}
