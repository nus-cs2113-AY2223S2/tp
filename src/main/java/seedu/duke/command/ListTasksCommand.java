package seedu.duke.command;

import seedu.duke.task.Task;
import seedu.duke.Ui;

import java.util.ArrayList;

/**
 * Command for displaying the current task list.
 */
public class ListTasksCommand extends Command {
    public static final String KEYWORD = "list";
    private final ArrayList<Task> tasks;

    /**
     * Constructs a command that will display the current task list.
     *
     * @param tasks The current task list.
     */
    public ListTasksCommand(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Displays the current task list.
     */
    public void execute() {
        Ui.listTasks(tasks);
    }
}
