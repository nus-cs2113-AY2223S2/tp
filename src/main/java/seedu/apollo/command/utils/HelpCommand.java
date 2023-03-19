package seedu.apollo.command.utils;

import seedu.apollo.storage.Storage;
import seedu.apollo.command.Command;
import seedu.apollo.module.ModuleList;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Ui;

/**
 * Help Command class that shows user a list of all available Commands.
 */
public class HelpCommand extends Command {

    /**
     * Prints list of all available Commands and their abilities.
     *
     * @param ui Prints list of Commands to user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList, ModuleList allModules) {
        ui.printHelpMessage();
    }

}
