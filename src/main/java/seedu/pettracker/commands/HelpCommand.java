package seedu.pettracker.commands;

import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;

public class HelpCommand extends Command {
    public HelpCommand() {
        super();
    }

    /**
     * Executes the given command
     *
     * @param ui Ui to do printing if required
     * @param storage Storage to save files if required
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        ui.showHelpMessage();
    }

    //TODO: Implement this method

    @Override
    public String[] parseArgs(String commandArgs) {
        return new String[0];
    }

    /**
     * Sets isExit to be true to exit the program
     * @return isExit boolean value for program to exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
