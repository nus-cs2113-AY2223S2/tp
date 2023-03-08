package seedu.pettracker.commands;

import seedu.pettracker.ui.Ui;

public class RemoveStatCommand extends Command {
    protected String petName;
    protected String statName;
    public RemoveStatCommand(String commandArgs) {
        super();
        this.petName = parseArgs(commandArgs)[0];
        this.statName = parseArgs(commandArgs)[1];
    }
    //TODO: Implement this method

    /**
     * Executes the given command
     *
     * @param ui Ui to do printing if required
     */
    @Override
    public void execute(Ui ui) {
        //petIndex = petList.find(petName);
        //petList.get(petIndex).removeStat(statName);
    }

    //TODO: Implement this method
    @Override
    public String[] parseArgs(String commandArgs) {
        String[] temp = new String[1];
        temp[0] = commandArgs;
        return temp;
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
