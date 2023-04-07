package seedu.pettracker.commands;

import seedu.pettracker.exceptions.InvalidTaskNameException;
import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;
import seedu.pettracker.data.TaskList;

import java.time.LocalDate;

public class AddTaskCommand extends Command {
    protected String todoDescription;
    protected LocalDate deadline;

    public AddTaskCommand(String description) {
        super();
        this.todoDescription = description;
    }

    public AddTaskCommand(String description, LocalDate deadline) {
        super();
        this.todoDescription = description;
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
        try {
            if (this.deadline != null) {
                TaskList.addTask(todoDescription, deadline);
            } else {
                TaskList.addTask(todoDescription);
            }
            TaskList.saveTasksToStorage(storage, ui);
            ui.addTodoCommandMessage();
        } catch (InvalidTaskNameException e) {
            ui.printInvalidTaskNameMessage();
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
