package seedu.todolist.logic.command;

import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

/**
 * Command class that will display all tags in the given task list, and delete tags from the list if requested.
 */
public class TagCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printTagInfo(taskList.getTags().size(), String.join(" ", taskList.getTags()));
    }
}
