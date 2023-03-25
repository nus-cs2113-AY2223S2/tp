package seedu.pettracker.commands;

import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;

public abstract class Command {

    /**
     * Executes the given command
     *
     * @param ui Ui to do printing if required
     */
    public abstract void execute(Ui ui, Storage storage);

    /**
     * Parses the arguments of the command
     *
     * @param commandArgs String containing the arguments of the command
     * @return String array containing the arguments of the command
     */
    public abstract String[] parseArgs(String commandArgs);

    /**
     * Sets isExit to be true to exit the program
     *
     * @return isExit boolean value for program to exit
     */
    public abstract boolean isExit();
}
