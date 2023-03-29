package seedu.pettracker.commands;

import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;
import seedu.pettracker.data.TaskList;

public class EditTaskCommand extends Command {

    protected int taskNumber;
    protected String newDescription;
    protected String newTime;


    public EditTaskCommand(String commandArgs) {
        super();
        String[] parsed = parseArgs(commandArgs);
        assert parsed.length > 0 : "no arguments";
        this.taskNumber = Integer.parseInt(parsed[0]);
        this.newDescription = parsed[1];
        if(parsed.length > 2) {
            this.newTime = parsed[2];
        }
        else {
            this.newTime = "";
        }
    }

    // TODO: Implement this method

    /**
     * Executes the given command
     *
     * @param ui Ui to do printing if required
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        TaskList.editTask(taskNumber, newDescription, newTime);
        TaskList.saveTasksToStorage(storage, ui);
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
        String[] split = commandArgs.split(" ", 2);
        String[] timeSplit = split[1].split("\\at");
        if (timeSplit.length > 1) {
            return new String[] {split[0], timeSplit[0], timeSplit[1]};
        } else {
            return new String[] {split[0], split[1]};
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
