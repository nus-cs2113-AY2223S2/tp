package seedu.pettracker.commands;

import seedu.pettracker.exceptions.EmptyTaskNameException;
import seedu.pettracker.exceptions.InvalidTaskNameException;
import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;
import seedu.pettracker.data.TaskList;

import java.time.LocalDate;

/**
 * This class represents an EditTaskcommand, which is when a user wants
 * to edit the description or date of an existing class
 */
public class EditTaskCommand extends Command {

    protected int taskNumber;
    protected String newDescription;
    protected LocalDate deadline;


    /**
     * Constructor with only new description for EditTaskCommand
     * @param taskNumber task number
     * @param newDescription description to be changed to
     */
    public EditTaskCommand(int taskNumber, String newDescription) {
        super();
        this.taskNumber = taskNumber;
        this.newDescription = newDescription;
    }

    /**
     * Constructor with date AND new description for EditTaskCommand
     * @param taskNumber task number
     * @param newDescription description to be changed to
     * @param deadline new deadline
     */
    public EditTaskCommand(int taskNumber, String newDescription, LocalDate deadline) {
        super();
        this.taskNumber = taskNumber;
        this.newDescription = newDescription;
        this.deadline = deadline;
    }

    /**
     * Executes the given command
     *
     * @param ui      Ui to do printing if required
     * @param storage Storage to save files if required
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        if (taskNumber <= 0) {
            ui.invalidTaskNumber();
            return;
        }
        try {
            TaskList.editTask(taskNumber, newDescription, deadline);
            TaskList.saveTasksToStorage(storage, ui);
            ui.editTaskCommandMessage(taskNumber, newDescription);
        } catch (IndexOutOfBoundsException e) {
            ui.taskNumberOutOfBoundsMessage();
        } catch (InvalidTaskNameException e) {
            ui.printInvalidTaskNameMessage();
        } catch (EmptyTaskNameException e) {
            ui.printEmptyTaskMessage();
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
