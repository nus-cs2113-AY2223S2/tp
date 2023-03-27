package seedu.todolist.logic.command;

import seedu.todolist.exception.InvalidEditException;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.constants.Flags;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.ui.Ui;
import seedu.todolist.task.TaskList;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;

//@@author clement559
public class EditCommand extends Command  {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_EDIT,
        Flags.DESCRIPTION, Flags.EMAIL, Flags.DEADLINE, Flags.TAG};

    public int index;
    public String description;
    public String email;
    public LocalDateTime deadline;
    private HashSet<String> tags;

    public EditCommand(HashMap<Flags, String> args) throws ToDoListException {
        index = ParserUtil.parseId(args.get(Flags.COMMAND_EDIT));
        description = ParserUtil.parseDescription(args.get(Flags.DESCRIPTION));
        deadline = ParserUtil.parseDeadline(args.get(Flags.DEADLINE));
        email = ParserUtil.parseEmail(args.get(Flags.EMAIL));
        tags = ParserUtil.parseTags(args.get(Flags.TAG));
        if (description == null && deadline == null && email == null && tags.isEmpty()) {
            throw new InvalidEditException();
        }
        assert index >= 0 : "Invalid index contained in variable";
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIdException {
        if (description != null) {
            taskList.setDescription(index, description);
        }
        if (deadline != null) {
            taskList.setDeadline(index, deadline);
        }
        if (email != null) {
            taskList.setEmail(index, email);
        }
        if (!tags.isEmpty()) {
            taskList.addTags(index, tags);
        }
        ui.printEditTaskMessage(taskList.getTaskString(index));
    }
}
