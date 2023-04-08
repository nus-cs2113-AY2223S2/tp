//@@author RuiShengGit
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.exception.InvalidSelectException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.model.Config;
import seedu.todolist.model.Task;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Predicate;

/**
 * Command class that will mark Task objects of the given TaskList object as incomplete.
 */
public class UnmarkTaskCommand extends Command{
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_UNMARK,
        Flags.FILTER_DONE, Flags.FILTER_OVERDUE, Flags.FILTER_BEFORE, Flags.FILTER_AFTER, Flags.FILTER_ALL,
        Flags.DESCRIPTION, Flags.EMAIL, Flags.REPEAT, Flags.TAG, Flags.PRIORITY};

    private HashSet<Integer> idHashSet;
    private Predicate<Task> predicate;

    /**
     * Constructs an UnmarkTaskCommand object by parsing the provided arguments.
     * Can select tasks to unmark by providing a list of ids, or one or more filters.
     *
     * @param args The provided arguments, parsed from the user's input.
     * @throws ToDoListException If ids/filters are invalid, or if both ids and filters are provided.
     */
    public UnmarkTaskCommand(HashMap<Flags, String> args) throws ToDoListException {
        idHashSet = ParserUtil.parseId(args.get(Flags.COMMAND_UNMARK));
        predicate = ParserUtil.parseFilter(args);
        if (idHashSet.isEmpty() == (predicate == null)) {
            throw new InvalidSelectException();
        }
    }

    /**
     * Marks the tasks specified in the constructor as incomplete.
     *
     * @param taskList The task list to unmark tasks from.
     * @param ui The Ui object used to display the result of marking tasks as incomplete.
     * @throws InvalidIdException If the given task list does not contain tasks with the specified ids.
     */
    @Override
    public void execute(TaskList taskList, Config config, Ui ui) throws InvalidIdException {
        String taskString = predicate == null
                ? taskList.setDone(idHashSet, false)
                : taskList.setDone(predicate, false);

        if (taskString.isEmpty()) {
            ui.printFilteredNoTasksFoundMessage();
        } else {
            ui.printUnmarkTaskMessage(taskString);
        }
    }
}
