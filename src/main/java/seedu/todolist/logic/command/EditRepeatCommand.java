//@@author clement559
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidEditException;
import seedu.todolist.exception.InvalidSelectException;
import seedu.todolist.exception.ToDoListException;
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

public class EditRepeatCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_EDIT_REPEAT, Flags.EDIT, Flags.EDIT_DELETE,
            Flags.FILTER_DONE, Flags.FILTER_OVERDUE, Flags.DESCRIPTION, Flags.EMAIL,
            Flags.FILTER_BEFORE, Flags.FILTER_AFTER, Flags.REPEAT, Flags.TAG, Flags.PRIORITY};

    private HashSet<Integer> idHashSet;
    private String repeatDurationString;
    Predicate<Task> predicate;

    public EditRepeatCommand(HashMap<Flags, String> args) throws ToDoListException {
        idHashSet = ParserUtil.parseId(args.get(Flags.COMMAND_EDIT_REPEAT));
        if (!Collections.disjoint(args.keySet(), Flags.filterFlags)) {
            // At least one filter flag is present
            predicate = ParserUtil.parseFilter(args);
        }
        if (idHashSet.isEmpty() == (predicate == null)) {
            throw new InvalidSelectException();
        }
        if (args.containsKey(Flags.EDIT) == args.containsKey(Flags.EDIT_DELETE)) {
            throw new InvalidEditException();
        } else if (args.containsKey(Flags.EDIT)) {
            repeatDurationString = args.get(Flags.EDIT);
        }
        assert args.size() > 1: "Fewer arguments than expected!";
    }

    @Override
    public void execute(TaskList taskList, Config config, Ui ui) throws ToDoListException {
        int repeatDuration = ParserUtil.parseRepeatDuration(repeatDurationString, LocalDateTime.MIN);
        String taskString = predicate == null
                ? taskList.setRepeatDuration(idHashSet, repeatDuration)
                : taskList.setRepeatDuration(predicate, repeatDuration);

        if (taskString.isEmpty()) {
            ui.printFilteredNoTasksFoundMessage();
        } else if (repeatDuration == 0) {
            ui.printEditDeleteTaskMessage("repeat times", taskString);
        } else {
            ui.printEditTaskMessage("repeat times", repeatDurationString, taskString);
        }
    }
}
