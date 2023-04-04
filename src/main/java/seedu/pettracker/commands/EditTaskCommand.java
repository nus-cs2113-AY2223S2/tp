package seedu.pettracker.commands;

import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;
import seedu.pettracker.data.TaskList;

import java.time.LocalDate;


public class EditTaskCommand extends Command {

    protected int taskNumber;
    protected String newDescription;
    protected LocalDate deadline;


    public EditTaskCommand(int taskNumber, String newDescription) {
        super();
        this.taskNumber = taskNumber;
        this.newDescription = newDescription;
    }
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
