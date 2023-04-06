//@@author KedrianLoh
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidBooleanException;
import seedu.todolist.exception.InvalidSortException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.model.Config;
import seedu.todolist.model.Task;
import seedu.todolist.ui.Ui;
import seedu.todolist.model.TaskList;

import java.util.Comparator;
import java.util.HashMap;
import java.util.function.Predicate;

import static java.util.function.Predicate.not;

/**
 * Command for displaying the current task list.
 */
public class ListTasksCommand extends Command {
    // @@author clement559
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_LIST,
        Flags.FILTER_DONE, Flags.FILTER_OVERDUE, Flags.SORT};

    private Predicate<Task> predicate;
    private Comparator<Task> comparator = null;

    /**
     * Constructs a ListTaskCommand object by parsing the provided arguments.
     *
     * @param args The provided arguments, parsed from the user's input.
     * @throws InvalidBooleanException If any of the provided boolean values are invalid.
     */
    public ListTasksCommand(HashMap<Flags, String> args) throws InvalidBooleanException, InvalidSortException {
        predicate = ParserUtil.parseFilter(args);
        if (args.containsKey(Flags.SORT)) {
            comparator = ParserUtil.parseSort(args.get(Flags.SORT));
        }
    }

    /**
     * Displays the full or filtered task list, depending on filters chosen.
     */
    public void execute(TaskList taskList, Config config, Ui ui) {
        int taskListSize = taskList.size(predicate);
        String taskListString = comparator == null
                ? taskList.toString(predicate) : taskList.toString(predicate, comparator);
        ui.printTaskList(taskListSize, taskListString);
    }
}
