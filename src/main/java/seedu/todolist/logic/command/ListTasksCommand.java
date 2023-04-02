package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.task.Task;
import seedu.todolist.ui.Ui;
import seedu.todolist.task.TaskList;

import java.util.HashMap;

//@@author KedrianLoh
/**
 * Command for displaying the current task list.
 */
public class ListTasksCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_LIST,
            Flags.FILTER};

    private TaskList filteredTaskList;
    String filter;
    /**
     * Displays the current task list.
     */

    /**
     * Constructs an ListTaskCommand object by parsing the provided arguments.
     * Optional parameters are allowed to be null.
     *
     * @param args The provided arguments, parsed from the user's input.
     * @throws ToDoListException If any of the provided arguments are invalid.
     */
    public ListTasksCommand(HashMap<Flags, String> args) throws ToDoListException {
        filter = ParserUtil.parseDescription(args.get(Flags.FILTER));
    }
    public void execute(TaskList taskList, Ui ui) {
        if (filter.isEmpty()) {
            ui.printTaskList(taskList.size(), taskList.toString(Task.deadlineComparator));
        } else {
            filteredTaskList = taskList.getFilteredTasks(filter);
            ui.printTaskList(filteredTaskList.size(), filteredTaskList.toString(Task.deadlineComparator));
        }
    }
}
