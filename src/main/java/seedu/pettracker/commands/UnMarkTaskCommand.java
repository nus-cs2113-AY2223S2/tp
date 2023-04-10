package seedu.pettracker.commands;

import seedu.pettracker.exceptions.IllegalArgException;
import seedu.pettracker.exceptions.TaskAlreadyIncompleteException;
import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;
import seedu.pettracker.data.TaskList;

public class UnMarkTaskCommand extends Command{
    protected int taskNumber;
    final String NEGATIVE_ARG_MESSAGE = "Task number must be a positive integer";
    public UnMarkTaskCommand(int taskNumber) throws IllegalArgException {
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
            checkIfIncomplete(taskNumber);
            TaskList.markTask(taskNumber, false);
            TaskList.saveTasksToStorage(storage, ui);
            String unmarkTaskDescription = TaskList.getSpecificTaskDescription(taskNumber);
            ui.unmarkTaskCommandMessage(unmarkTaskDescription);
        } catch (IndexOutOfBoundsException e) {
            ui.taskNumberOutOfBoundsMessage();
        } catch (TaskAlreadyIncompleteException e) {
            ui.taskAlreadyIncompleteMessage();
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
     * Checks if a task being marked as incomplete is already incomplete
     * @param taskId id of task to check
     * @throws TaskAlreadyIncompleteException if the task is already incomplete
     */
    public static void checkIfIncomplete(int taskId) throws TaskAlreadyIncompleteException {
        if (!TaskList.getTaskStatus(taskId)) {
            throw new TaskAlreadyIncompleteException();
        }
    }
}
