package seedu.pettracker.commands;

import seedu.pettracker.ui.Ui;

public abstract class Command {
    private static String keyword;

    /**
     * Executes the given command
     * @param ui Ui to do printing if required
     */
    public abstract void execute(Ui ui);

    public abstract String[] parseArgs(String commandArgs);
}
