//@@author clement559
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidEditException;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.FormatterUtil;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.model.Config;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;

public class EditDeadlineCommand extends Command  {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_EDIT_DEADLINE, Flags.EDIT, Flags.EDIT_DELETE};

    private HashSet<Integer> idHashSet;
    private LocalDateTime deadline;

    public EditDeadlineCommand(HashMap<Flags, String> args) throws ToDoListException {
        idHashSet = ParserUtil.parseId(args.get(Flags.COMMAND_EDIT_DEADLINE));
        if (args.containsKey(Flags.EDIT) == args.containsKey(Flags.EDIT_DELETE)) {
            throw new InvalidEditException();
        } else if (args.containsKey(Flags.EDIT)) {
            deadline = ParserUtil.parseDeadline(args.get(Flags.EDIT));
        }
    }

    @Override
    public void execute(TaskList taskList, Config config, Ui ui) throws InvalidIdException {
        String taskString = taskList.setDeadline(idHashSet, deadline);
        if (deadline == null) {
            ui.printEditDeleteTaskMessage("deadline", taskString);
        } else {
            ui.printEditTaskMessage("deadline", FormatterUtil.getDeadlineAsString(deadline), taskString);
        }
    }
}
