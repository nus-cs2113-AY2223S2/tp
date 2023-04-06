//@@author clement559
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidEditException;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.exception.InvalidSelectException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.FormatterUtil;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.model.Config;
import seedu.todolist.model.Task;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Predicate;

public class EditDeadlineCommand extends Command  {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_EDIT_DEADLINE, Flags.EDIT, Flags.EDIT_DELETE,
        Flags.FILTER_DONE, Flags.FILTER_OVERDUE, Flags.DESCRIPTION, Flags.EMAIL,
        Flags.FILTER_BEFORE, Flags.FILTER_AFTER, Flags.REPEAT, Flags.TAG, Flags.PRIORITY};

    private HashSet<Integer> idHashSet;
    private LocalDateTime deadline;
    private Predicate<Task> predicate;

    public EditDeadlineCommand(HashMap<Flags, String> args) throws ToDoListException {
        idHashSet = ParserUtil.parseId(args.get(Flags.COMMAND_EDIT_DEADLINE));
        if (!Collections.disjoint(args.keySet(), Flags.FILTER_FLAGS)) {
            // At least one filter flag is present
            predicate = ParserUtil.parseFilter(args);
        }
        if (idHashSet.isEmpty() == (predicate == null)) {
            throw new InvalidSelectException();
        }
        if (args.containsKey(Flags.EDIT) == args.containsKey(Flags.EDIT_DELETE)) {
            throw new InvalidEditException();
        } else if (args.containsKey(Flags.EDIT)) {
            deadline = ParserUtil.parseDeadline(args.get(Flags.EDIT));
        }
        assert args.size() > 1: "Fewer arguments than expected!";
    }

    @Override
    public void execute(TaskList taskList, Config config, Ui ui) throws InvalidIdException {
        String taskString = predicate == null
                ? taskList.setDeadline(idHashSet, deadline)
                : taskList.setDeadline(predicate, deadline);

        if (taskString.isEmpty()) {
            ui.printFilteredNoTasksFoundMessage();
        } else if (deadline == null) {
            ui.printEditDeleteTaskMessage("deadline", taskString);
        } else {
            ui.printEditTaskMessage("deadline", FormatterUtil.getDeadlineAsString(deadline), taskString);
        }
    }
}
