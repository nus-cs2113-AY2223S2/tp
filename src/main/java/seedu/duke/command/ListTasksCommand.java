package seedu.duke.command;

import seedu.duke.task.Task;
import seedu.duke.Ui;
import seedu.duke.task.TaskList;

import java.util.ArrayList;

/**
 * Command for displaying the current task list.
 */
public class ListTasksCommand extends Command {
    public static final String KEYWORD = "list";

    /**
     * Displays the current task list.
     */
    public void execute(TaskList taskList, Ui ui) {
        ui.listTasks(taskList);
    }
}
