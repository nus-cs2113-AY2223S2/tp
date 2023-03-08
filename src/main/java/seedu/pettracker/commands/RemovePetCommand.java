package seedu.pettracker.commands;

import seedu.pettracker.ui.Ui;

public class RemovePetCommand extends Command{
    protected String petName;
    public RemovePetCommand(String commandArgs) {
        super();
        this.petName = parseArgs(commandArgs)[0];
    }

    //TODO: Implement this method

    /**
     * Executes the given command
     *
     * @param ui Ui to do printing if required
     */
    @Override
    public void execute(Ui ui) {
        //petList.removePet(petName);
    }

    //TODO: Implement this method

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
     * @return isExit boolean value for program to exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
