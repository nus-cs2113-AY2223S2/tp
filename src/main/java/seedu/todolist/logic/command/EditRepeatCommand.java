//@@author clement559
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidEditException;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.exception.InvalidSelectException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.model.Config;
import seedu.todolist.model.Task;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Predicate;

/**
 * Command class that will edit/delete the repeat count of Task objects of the given TaskList object.
 */
public class EditRepeatCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_EDIT_REPEAT, Flags.EDIT, Flags.EDIT_DELETE,
        Flags.FILTER_DONE, Flags.FILTER_OVERDUE, Flags.FILTER_BEFORE, Flags.FILTER_AFTER, Flags.FILTER_ALL,
        Flags.DESCRIPTION, Flags.EMAIL, Flags.REPEAT, Flags.TAG, Flags.PRIORITY};

    private String repeatTimesString;
    private HashSet<Integer> idHashSet;
    private Predicate<Task> predicate;

    /**
     * Constructs a EditRepeatCommand object by parsing the provided arguments.
     *
     * @param args The provided arguments, parsed from the user's input.
     * @throws InvalidSelectException If no task ids are provided.
     * @throws InvalidEditException If neither/both edit and delete keywords are provided.
     */
    public EditRepeatCommand(HashMap<Flags, String> args) throws ToDoListException {
        idHashSet = ParserUtil.parseId(args.get(Flags.COMMAND_EDIT_REPEAT));
        predicate = ParserUtil.parseFilter(args);
        if (idHashSet.isEmpty() == (predicate == null)) {
            throw new InvalidSelectException();
        }
        if (args.containsKey(Flags.EDIT) == args.containsKey(Flags.EDIT_DELETE)) {
            throw new InvalidEditException();
        } else if (args.containsKey(Flags.EDIT)) {
            repeatTimesString = args.get(Flags.EDIT);
        }
        assert args.size() > 1: "Fewer arguments than expected!";
    }

    /**
     * Edits the repeat count for the tasks provided in the constructor.
     *
     * @param taskList The task list to edit tasks from.
     * @param ui The Ui object used to display the result of editing repeat count.
     * @throws InvalidIdException If the given task list does not contain tasks with the specified ids.
     */
    @Override
    public void execute(TaskList taskList, Config config, Ui ui) throws ToDoListException {
        int repeatTimes = ParserUtil.parseRepeatTimes(repeatTimesString, LocalDateTime.MIN);
        String taskString = predicate == null
                ? taskList.setRepeatTimes(idHashSet, repeatTimes)
                : taskList.setRepeatTimes(predicate, repeatTimes);

        if (taskString.isEmpty()) {
            ui.printFilteredNoTasksFoundMessage();
        } else if (repeatTimes == 0) {
            ui.printEditDeleteTaskMessage("repeat times", taskString);
        } else {
            ui.printEditTaskMessage("repeat times", repeatTimesString, taskString);
        }
    }
}
