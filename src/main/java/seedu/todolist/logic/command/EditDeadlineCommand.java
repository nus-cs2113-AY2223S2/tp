package seedu.todolist.logic.command;

import seedu.todolist.exception.InvalidEditException;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.constants.Flags;
import seedu.todolist.logic.FormatterUtil;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.ui.Ui;
import seedu.todolist.task.TaskList;

import java.time.LocalDateTime;
import java.util.HashMap;

//@@author clement559
public class EditDeadlineCommand extends Command  {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_EDIT_DEADLINE, Flags.EDIT, Flags.EDIT_DELETE};

    private int id;
    private LocalDateTime deadline;

    public EditDeadlineCommand(HashMap<Flags, String> args) throws ToDoListException {
        id = ParserUtil.parseId(args.get(Flags.COMMAND_EDIT_DEADLINE));
        if (args.containsKey(Flags.EDIT)) {
            deadline = ParserUtil.parseDeadline(args.get(Flags.EDIT));
        } else if (!args.containsKey(Flags.EDIT_DELETE)) {
            throw new InvalidEditException();
        }
        assert id >= 0 : "Invalid id contained in variable";
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIdException {
        String taskString = taskList.setDeadline(id, deadline);
        if (deadline == null) {
            taskList.setRepeatDuration(id, 0);
            ui.printEditDeleteTaskMessage("deadline", taskString);
        } else {
            ui.printEditTaskMessage("deadline", FormatterUtil.getDeadlineAsString(deadline), taskString);
        }
    }
}
