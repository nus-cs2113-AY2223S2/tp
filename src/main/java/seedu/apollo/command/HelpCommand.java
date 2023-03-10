package seedu.apollo.command;

import seedu.apollo.Storage;
import seedu.apollo.module.ModuleList;
import seedu.apollo.task.TaskList;
import seedu.apollo.Ui;

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
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList) {
        ui.printHelpMessage();
    }

}
