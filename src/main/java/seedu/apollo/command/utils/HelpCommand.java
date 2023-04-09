package seedu.apollo.command.utils;

import seedu.apollo.calendar.Calendar;
import seedu.apollo.storage.Storage;
import seedu.apollo.command.Command;
import seedu.apollo.module.ModuleList;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Ui;

/**
 * Parent class of all help commands
 */
public class HelpCommand extends Command {

    public HelpCommand() {
        super("HelpCommand");
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
