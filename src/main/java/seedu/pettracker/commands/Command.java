package seedu.pettracker.commands;

import seedu.pettracker.ui.Ui;

public abstract class Command {
    private static String command;

    /**
     * Executes the given command
     * @param ui Ui to do printing if required
     */
    public abstract void execute(Ui ui);
    public abstract boolean isExit();
}
