package seedu.todolist.logic.command;

import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

public class HelpCommand extends Command{
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printHelpList();
    }
}
