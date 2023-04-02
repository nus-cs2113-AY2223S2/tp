package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidFlagException;
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
    // @@author clement559
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_LIST,
            Flags.FILTER_DONE, Flags.FILTER_UNDONE, Flags.FILTER_OVERDUE};

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
        if (args.containsKey(Flags.FILTER_OVERDUE)) {
            filter = "overdue";
        } else if (args.containsKey(Flags.FILTER_DONE)) {
            filter = "done";
        } else if (args.containsKey(Flags.FILTER_UNDONE)) {
            filter = "undone";
        }
    }
    public void execute(TaskList taskList, Ui ui) throws InvalidFlagException {
        if (filter == null) {
            ui.printTaskList(taskList.size(), taskList.toString(Task.deadlineComparator));
        } else if (filter.equals("done") || filter.equals("undone") || filter.equals("overdue")){
            filteredTaskList = taskList.getFilteredTasks(filter);
            ui.printTaskList(filteredTaskList.size(), filteredTaskList.toString(Task.deadlineComparator));
        } else {
            throw new InvalidFlagException(filter);
        }
    }
}
