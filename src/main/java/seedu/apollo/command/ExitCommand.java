package seedu.apollo.command;

import seedu.apollo.Storage;
import seedu.apollo.task.TaskList;
import seedu.apollo.Ui;

/**
 * Exit Command class that allows user to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Prints exit message.
     * Sets the exit status of the Command to be true.
     *
     * @param ui Prints exit message to user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
        this.setExit();
    }

}
