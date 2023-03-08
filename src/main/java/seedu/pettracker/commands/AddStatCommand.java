package seedu.pettracker.commands;

import seedu.pettracker.ui.Ui;

public class AddStatCommand extends Command {
    protected String petName;
    protected String statName;
    protected String statValue;
    public AddStatCommand(String commandArgs) {
        super();
        this.petName = parseArgs(commandArgs)[0];
        this.statName = parseArgs(commandArgs)[1];
        this.statValue = parseArgs(commandArgs)[2];
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
        //petList.get(petIndex).addStat(statName, statValue);
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
        return new String[2];
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
