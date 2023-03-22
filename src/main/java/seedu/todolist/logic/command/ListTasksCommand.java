package seedu.todolist.logic.command;

import seedu.todolist.ui.Ui;
import seedu.todolist.task.TaskList;

/**
 * Command for displaying the current task list.
 */
public class ListTasksCommand extends Command {
    public static final String KEYWORD = "list";

    //@@ KedrianLoh
    /**
     * Displays the current task list.
     */
    public void execute(TaskList taskList, Ui ui) {
        taskList.sortByDeadline();
        ui.printTaskList(taskList.size(), taskList.toString());
    }
}
