package seedu.pettracker.commands;

import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;
import seedu.pettracker.data.TaskList;

import seedu.pettracker.exceptions.IllegalArgException;

public class RemoveTaskCommand extends Command{

    protected int taskNumber;

    public RemoveTaskCommand(int taskNumber) throws IllegalArgException{
        super();
        if (taskNumber <= 0) {
            throw new IllegalArgException("Task number must be a positive integer");
        }
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the given command
     *
     * @param ui Ui to do printing if required
     * @param storage Storage to save files if required
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        try {
            TaskList.removeTask(taskNumber);
            TaskList.saveTasksToStorage(storage, ui);
            ui.removeTaskCommandMessage(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            ui.taskNumberOutOfBoundsMessage();
        }
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
