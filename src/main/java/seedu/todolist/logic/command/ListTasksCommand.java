//@@author KedrianLoh
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidBooleanException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.model.Config;
import seedu.todolist.model.Task;
import seedu.todolist.ui.Ui;
import seedu.todolist.model.TaskList;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Predicate;

/**
 * Command for displaying the current task list.
 */
public class ListTasksCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_LIST, Flags.SORT,
        Flags.FILTER_DONE, Flags.FILTER_OVERDUE, Flags.FILTER_BEFORE, Flags.FILTER_AFTER, Flags.FILTER_ALL,
        Flags.DESCRIPTION, Flags.EMAIL, Flags.REPEAT, Flags.TAG, Flags.PRIORITY};

    private Predicate<Task> predicate;
    private Comparator<Task> comparator = null;

    /**
     * Constructs a ListTaskCommand object by parsing the provided arguments.
     *
     * @param args The provided arguments, parsed from the user's input.
     * @throws InvalidBooleanException If any of the provided boolean values are invalid.
     */
    public ListTasksCommand(HashMap<Flags, String> args) throws ToDoListException {
        predicate = ParserUtil.parseFilter(args);
        if (args.containsKey(Flags.SORT)) {
            comparator = ParserUtil.parseSort(args.get(Flags.SORT));
        }
    }

    /**
     * Displays the full or filtered task list, depending on filters chosen.
     */
    public void execute(TaskList taskList, Config config, Ui ui) {
        if (predicate == null && comparator == null) {
            // Display full unsorted task list
            ui.printTaskList(taskList.size(), taskList.toString());
        } else if (predicate == null) {
            // Display full sorted task list
            ui.printTaskList(taskList.size(), taskList.toString(comparator));
        } else if (comparator == null) {
            // Display filtered unsorted task list
            ui.printTaskList(taskList.size(predicate), taskList.toString(predicate));
        } else {
            // Display filtered sorted task list
            ui.printTaskList(taskList.size(predicate), taskList.toString(predicate, comparator));
        }
    }
}
