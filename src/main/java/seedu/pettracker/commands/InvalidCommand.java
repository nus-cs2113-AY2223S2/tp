package seedu.pettracker.commands;

import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;

public class InvalidCommand extends Command{
    protected String exceptionMessage;
    public InvalidCommand(String exceptionMessage) {
        super();
        this.exceptionMessage = exceptionMessage;
    }

    /**
     * Executes the given command
     *
     * @param ui Ui to do printing if required
     * @param storage Storage to save files if required
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        System.out.println(exceptionMessage);
    }

    /**
     * Sets isExit to be true to exit the program
     *
     * @return isExit boolean value for program to exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
