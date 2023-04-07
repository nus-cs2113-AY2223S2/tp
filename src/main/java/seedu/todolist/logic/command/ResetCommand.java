package seedu.todolist.logic.command;

import seedu.todolist.model.Config;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;

public class ResetCommand extends Command {
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
