package seedu.apollo.command.utils;

import seedu.apollo.calendar.Calendar;
import seedu.apollo.storage.Storage;
import seedu.apollo.command.Command;
import seedu.apollo.module.ModuleList;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Ui;

import java.util.logging.Logger;

/**
 * Parent class of all help commands
 */
public class HelpCommand extends Command {

    private static Logger logger = Logger.getLogger("HelpCommand");

    public HelpCommand() {
        super(logger);
    }

    /**
     * Prints list of all available Commands and their abilities.
     *
     * @param ui Prints list of Commands to user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList, ModuleList allModules,
                        Calendar calendar) {
        ui.printHelpMessage();

    }

}
