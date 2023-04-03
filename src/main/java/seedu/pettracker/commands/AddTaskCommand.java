package seedu.pettracker.commands;

import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;
import seedu.pettracker.data.TaskList;

import java.time.LocalDate;

public class AddTaskCommand extends Command {
    protected String todoDescription;
    protected LocalDate deadline;
    final String DEADLINE_REGEX = " /by ";

    public AddTaskCommand(String commandArgs) {
        super();
        if (commandArgs.contains(DEADLINE_REGEX)) {
            String[] args = commandArgs.split(DEADLINE_REGEX);
            try {
                this.todoDescription = args[0];
                this.deadline = LocalDate.parse(args[1]);
            } catch (Exception e) {
                this.todoDescription = args[0];
                this.deadline = null;
            }
        } else {
            this.todoDescription = commandArgs;
            this.deadline = null;
        }
    }

    /**
     * Executes the given command
     *
     * @param ui      Ui to do printing if required
     * @param storage Storage to save files if required
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        if (this.deadline != null) {
            TaskList.addTask(todoDescription, deadline);
        } else {
            TaskList.addTask(todoDescription);
        }
        TaskList.saveTasksToStorage(storage, ui);
        ui.addTodoCommandMessage();
    }

    /**
     * Parses the arguments of the command
     *
     * @param commandArgs String containing the arguments of the command
     * @return String array containing the arguments of the command
     */
    @Override
    public String[] parseArgs(String commandArgs) {
        return commandArgs.split("\\at");

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
