//@@author RuiShengGit
package seedu.todolist.logic.command;

import seedu.todolist.model.Config;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;

/**
 * Command class that will reset the given task list.
 */
public class ResetCommand extends Command {
    /**
     * Resets the given task list after obtaining confirmation from the user.
     *
     * @param taskList The task list to be reset.
     * @param ui The Ui object used to display the result of resetting the task list.
     */
    @Override
    public void execute(TaskList taskList, Config config, Ui ui) {
        if (ui.getUserConfirmation()) {
            taskList.reset();
            ui.printResetMessage(true);
        } else {
            ui.printResetMessage(false);
        }
    }
}
