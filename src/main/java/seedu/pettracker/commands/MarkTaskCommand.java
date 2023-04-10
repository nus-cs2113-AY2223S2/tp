package seedu.pettracker.commands;

import seedu.pettracker.exceptions.IllegalArgException;
import seedu.pettracker.exceptions.TaskAlreadyCompleteException;
import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;
import seedu.pettracker.data.TaskList;

public class MarkTaskCommand extends Command{
    protected int taskNumber;
    final String NEGATIVE_ARG_MESSAGE = "Task number must be a positive integer";
    public MarkTaskCommand(int taskNumber) throws IllegalArgException {
        super();
        if (taskNumber <= 0) {
            throw new IllegalArgException(NEGATIVE_ARG_MESSAGE);
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
            checkIfComplete(taskNumber);
            TaskList.markTask(taskNumber, true);
            TaskList.saveTasksToStorage(storage, ui);
            String markTaskDescription = TaskList.getSpecificTaskDescription(taskNumber);
            ui.markTaskCommandMessage(markTaskDescription);
        } catch (IndexOutOfBoundsException e) {
            ui.taskNumberOutOfBoundsMessage();
        } catch (TaskAlreadyCompleteException e) {
            ui.taskAlreadyCompleteMessage();
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

    /**
     * Checks if a task being marked as complete is already completed
     * @param taskId id of the task to check
     * @throws TaskAlreadyCompleteException if the task is already complete
     */
    public static void checkIfComplete(int taskId) throws TaskAlreadyCompleteException {
        if (TaskList.getTaskStatus(taskId)) {
            throw new TaskAlreadyCompleteException();
        }
    }
}
