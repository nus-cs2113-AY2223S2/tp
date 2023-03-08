package seedu.pettracker.commands;

import seedu.pettracker.ui.Ui;

public abstract class Command {
    private static String command;

    /**
     * Executes the command and returns the output based on the command given
     *
     * @return Result of the command
     */
    public abstract void execute(Ui ui);
    public abstract boolean isExit();
}
