package seedu.pettracker.commands;

import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;
import seedu.pettracker.data.TaskList;

public class UnMarkTaskCommand extends Command{
    protected int taskNumber;

    public UnMarkTaskCommand(String commandArgs) {
        super();
        this.taskNumber = Integer.parseInt(commandArgs);
    }

    /**
     * Executes the given command
     *
     * @param ui Ui to do printing if required
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        TaskList.markTask(taskNumber, false);
        TaskList.saveTasksToStorage(storage, ui);
        ui.unmarkTaskCommandMessage();
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
    @Override
    public boolean isExit() {
        return false;
    }
}
