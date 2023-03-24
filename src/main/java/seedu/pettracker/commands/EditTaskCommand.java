package seedu.pettracker.commands;

import seedu.pettracker.ui.Ui;
import seedu.pettracker.data.TaskList;

public class EditTaskCommand extends Command {

    protected int taskNumber;
    protected String newDescription;



    public EditTaskCommand(String commandArgs) {
        super();
        this.taskNumber = Integer.parseInt(parseArgs(commandArgs)[0]);
        this.newDescription = parseArgs(commandArgs)[1];
    }

    // TODO: Implement this method

    /**
     * Executes the given command
     *
     * @param ui Ui to do printing if required
     */
    @Override
    public void execute(Ui ui) {
        TaskList.editTask(taskNumber, newDescription);
        ui.editTaskCommandMessage(taskNumber, newDescription);
    }

    /**
     * Parses the arguments of the command
     *
     * @param commandArgs String containing the arguments of the command
     * @return String array containing the arguments of the command
     */
    @Override
    public String[] parseArgs(String commandArgs) {
        return commandArgs.split(" ", 2);
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
