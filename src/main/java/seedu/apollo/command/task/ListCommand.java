package seedu.apollo.command.task;

import seedu.apollo.storage.Storage;
import seedu.apollo.command.Command;
import seedu.apollo.module.ModuleList;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Ui;

/**
 * List Command class that shows user a list of all the Tasks they have input.
 */
public class ListCommand extends Command {

    /**
     * Prints out all Tasks from the TaskList.
     *
     * @param taskList The existing TaskList.
     * @param ui Prints shortlisted Tasks to user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList) {
        assert (ui != null & storage != null & taskList != null & moduleList != null) :
                "executing ListCommand";
        ui.printList(taskList);
    }

}
