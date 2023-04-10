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
     * Executes the given command
     *
     * @param ui Ui to do printing if required
     * @param storage Storage to save files if required
     */
    @Override
    public void execute(Ui ui, Storage storage) {
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
