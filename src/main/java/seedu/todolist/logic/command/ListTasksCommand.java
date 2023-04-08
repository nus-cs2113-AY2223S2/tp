//@@author KedrianLoh
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidBooleanException;
import seedu.todolist.exception.InvalidIdException;
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
 * Command class that will display a summary of Task objects from the given TaskList object.
 */
public class ListTasksCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_LIST, Flags.SORT,
        Flags.FILTER_DONE, Flags.FILTER_OVERDUE, Flags.FILTER_BEFORE, Flags.FILTER_AFTER, Flags.FILTER_ALL,
        Flags.DESCRIPTION, Flags.EMAIL, Flags.REPEAT, Flags.TAG, Flags.PRIORITY};

    private HashSet<Integer> idHashSet;
    private Predicate<Task> predicate;
    private Comparator<Task> comparator = null;

    /**
     * Constructs a ListTasksCommand object by parsing the provided arguments.
     * Can select tasks to display by providing a list of ids, or one or more filters.
     *
     * @param args The provided arguments, parsed from the user's input.
     * @throws ToDoListException If ids/filters are invalid, or if both ids and filters are provided.
     */
    public ListTasksCommand(HashMap<Flags, String> args) throws ToDoListException {
        idHashSet = ParserUtil.parseId(args.get(Flags.COMMAND_LIST));
        predicate = ParserUtil.parseFilter(args);
        if (args.containsKey(Flags.SORT)) {
            comparator = ParserUtil.parseSort(args.get(Flags.SORT));
        }
    }

    /**
     * Displays a summary of tasks specified in the constructor.
     *
     * @param taskList The task list to get task information from.
     * @param ui The Ui object used to display the task summary.
     * @throws InvalidIdException If the given task list does not contain tasks with the specified ids.
     */
    public void execute(TaskList taskList, Config config, Ui ui) throws InvalidIdException {
        if (idHashSet.isEmpty() && predicate == null) {
            if (comparator == null) {
                // Display full unsorted task list
                ui.printTaskList(taskList.size(), taskList.toString());
            } else {
                // Display full sorted task list
                ui.printTaskList(taskList.size(), taskList.toString(comparator));
            }
            return;
        }

        if (predicate == null) {
            if (comparator == null) {
                // Display filtered (by ids) unsorted task list
                ui.printTaskList(idHashSet.size(), taskList.toString(idHashSet));
            } else {
                // Display filtered (by ids) sorted task list
                ui.printTaskList(idHashSet.size(), taskList.toString(idHashSet, comparator));
            }
            return;
        }

        if (comparator == null) {
            // Display filtered (by predicate) unsorted task list
            ui.printTaskList(taskList.size(predicate), taskList.toString(predicate));
        } else {
            // Display filtered (by predicate) sorted task list
            ui.printTaskList(taskList.size(predicate), taskList.toString(predicate, comparator));
        }
    }
}
