package seedu.pettracker.commands;

import seedu.pettracker.ui.Ui;

public class AddStatCommand extends Command{
    private final String keyword = "add-stat";
    private final String statName;
    private final String statValue;

    public AddStatCommand(String commandArgs) {

        this.statName = parseArgs(commandArgs)[0];
        this.statValue = parseArgs(commandArgs)[1];
    }

    //TODO: Implement this method
    /**
     * Executes the given command
     *
     * @param ui Ui to do printing if required
     */
    @Override
    public void execute(Ui ui) {

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

}
