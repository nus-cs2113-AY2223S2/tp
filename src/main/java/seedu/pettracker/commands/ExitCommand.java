package seedu.pettracker.commands;

import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;

public class ExitCommand extends Command {
    // @@author shawntangy-reused
    // Reused from
    // https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/commands/ExitCommand.java
    // with minor modifications

    public ExitCommand() {
    }

    /**
     * @param ui Ui class to do prints
     */
    @Override
    public void execute(Ui ui, Storage storage) {

    }

    /**
     * Parses the arguments of the command
     *
     * @param commandArgs String containing the arguments of the command
     * @return String array containing the arguments of the command
     */
    @Override
    public String[] parseArgs(String commandArgs) {
        return new String[0];
    }

    /**
     * Sets isExit to be true to exit the program
     *
     * @return isExit boolean value for program to exit
     */
    public boolean isExit() {
        return true;
    }
}
