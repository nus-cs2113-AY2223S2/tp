//@@author RuiShengGit
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidSelectException;
import seedu.todolist.model.Priority;
import seedu.todolist.exception.InvalidEditException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.model.Config;
import seedu.todolist.model.Task;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Predicate;

public class EditPriorityCommand extends Command{
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_EDIT_PRIORITY, Flags.EDIT, Flags.EDIT_DELETE,
        Flags.FILTER_DONE, Flags.FILTER_OVERDUE, Flags.FILTER_BEFORE, Flags.FILTER_AFTER, Flags.FILTER_ALL,
        Flags.DESCRIPTION, Flags.EMAIL, Flags.REPEAT, Flags.TAG, Flags.PRIORITY};

    private Priority priority = Priority.NONE;
    private HashSet<Integer> idHashSet;
    private Predicate<Task> predicate;

    public EditPriorityCommand(HashMap<Flags, String> args) throws ToDoListException {
        idHashSet = ParserUtil.parseId(args.get(Flags.COMMAND_EDIT_PRIORITY));
        predicate = ParserUtil.parseFilter(args);
        if (idHashSet.isEmpty() == (predicate == null)) {
            throw new InvalidSelectException();
        }
        if (args.containsKey(Flags.EDIT) == args.containsKey(Flags.EDIT_DELETE)) {
            throw new InvalidEditException();
        } else if (args.containsKey(Flags.EDIT)) {
            priority = ParserUtil.parsePriority(args.get(Flags.EDIT));
        }
        assert args.size() > 1: "Fewer arguments than expected!";
    }

    @Override
    public void execute(TaskList taskList, Config config, Ui ui) throws ToDoListException {
        String taskString = predicate == null
                ? taskList.setPriority(idHashSet, priority)
                : taskList.setPriority(predicate, priority);

        if (taskString.isEmpty()) {
            ui.printFilteredNoTasksFoundMessage();
        } else if (priority == Priority.NONE) {
            ui.printEditDeleteTaskMessage("priority level", taskString);
        } else {
            ui.printEditTaskMessage("priority level", priority.toString(), taskString);
        }
    }
}
