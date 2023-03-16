package seedu.moneymind.command;

import seedu.moneymind.ui.Ui;

public interface Command {
    void execute(Ui ui);
    boolean isExit();
}
