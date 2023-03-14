package seedu.moneymind.command;

import seedu.moneymind.Ui;

public interface Command {
    void execute(Ui ui);
    boolean isExit();
}
