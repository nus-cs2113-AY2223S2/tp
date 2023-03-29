package seedu.pettracker.commands;

import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;
import seedu.pettracker.data.TaskList;

public class AddTaskCommand extends Command {

    protected String todoDescription;
    protected String time;

    public AddTaskCommand(String commandArgs) {
        super();
        String[] parsed = parseArgs(commandArgs);
        if (parsed.length > 1) {
            this.todoDescription = parsed[0];
            this.time = parsed[1];
        } else {
            this.todoDescription = parsed[0];
            this.time = "";
        }
    }

    /**
     * Executes the given command
     *
     * @param ui Ui to do printing if required
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        TaskList.addTask(todoDescription, time);
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
