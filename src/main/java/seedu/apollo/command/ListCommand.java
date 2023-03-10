package seedu.apollo.command;

import seedu.apollo.Storage;
import seedu.apollo.module.ModuleList;
import seedu.apollo.task.TaskList;
import seedu.apollo.Ui;

/**
 * List Command class that shows user a list of all the Tasks they have input.
 */
public class ListCommand extends Command {

    /**
     * Prints out all Tasks from the TaskList.
     *
     * @param tasks The existing TaskList.
     * @param ui Prints shortlisted Tasks to user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, ModuleList moduleList) {
        ui.printList(tasks.getAllTasks());
    }

}
