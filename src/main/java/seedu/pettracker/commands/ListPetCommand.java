package seedu.pettracker.commands;

import seedu.pettracker.ui.Ui;

public class ListPetCommand extends Command {
    public ListPetCommand() {
        super();
    }

    //TODO: Implement this method

    /**
     * Executes the given command
     *
     * @param ui Ui to do printing if required
     */
    @Override
    public void execute(Ui ui) {
        //petList.list();
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
