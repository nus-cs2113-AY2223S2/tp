package seedu.apollo.command.utils;

import seedu.apollo.calendar.Calendar;
import seedu.apollo.storage.Storage;
import seedu.apollo.command.Command;
import seedu.apollo.module.ModuleList;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Ui;

import java.util.logging.Logger;

/**
 * Exit Command class that allows user to exit the program.
 */
public class ExitCommand extends Command {

    private static Logger logger = Logger.getLogger("ExitCommand");

    public ExitCommand() {
        super(logger);
    }

    /**
     * Prints exit message.
     * Sets the exit status of the Command to be true.
     *
     * @param ui Prints exit message to user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList, ModuleList allModules,
                        Calendar calendar) {
        ui.printExitMessage();
        this.setExit();
    }

}
