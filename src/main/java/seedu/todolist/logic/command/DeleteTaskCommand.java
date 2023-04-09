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
 * Command class that will remove Task objects from the given TaskList object.
 */
public class DeleteTaskCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_DELETE,
        Flags.FILTER_DONE, Flags.FILTER_OVERDUE, Flags.FILTER_BEFORE, Flags.FILTER_AFTER, Flags.FILTER_ALL,
        Flags.DESCRIPTION, Flags.EMAIL, Flags.REPEAT, Flags.TAG, Flags.PRIORITY};

    private HashSet<Integer> idHashSet;
    private Predicate<Task> predicate;

    /**
     * Constructs a DeleteTaskCommand object by parsing the provided arguments.
     * Can select tasks to delete by providing a list of ids, or one or more filters.
     *
     * @param args The provided arguments, parsed from the user's input.
     * @throws ToDoListException If ids/filters are invalid, or if both ids and filters are provided.
     */
    public DeleteTaskCommand(HashMap<Flags, String> args) throws ToDoListException {
        idHashSet = ParserUtil.parseId(args.get(Flags.COMMAND_DELETE));
        predicate = ParserUtil.parseFilter(args);
        if (idHashSet.isEmpty() == (predicate == null)) {
            throw new InvalidSelectException();
        }
    }

    /**
     * Removes tasks specified in the constructor from the task list.
     *
     * @param taskList The task list to remove tasks from.
     * @param ui The Ui object used to display the result of deleting tasks.
     * @throws InvalidIdException If the given task list does not contain tasks with the specified ids.
     */
    @Override
    public void execute(TaskList taskList, Config config, Ui ui) throws InvalidIdException {
        if (predicate == null) {
            ui.printDeleteTaskMessage(taskList.deleteTask(idHashSet));
            return;
        }

        String taskString = taskList.deleteTask(predicate);
        if (taskString.isEmpty()) {
            ui.printFilteredNoTasksFoundMessage();
        } else {
            ui.printDeleteTaskMessage(taskString);
        }
    }
}
