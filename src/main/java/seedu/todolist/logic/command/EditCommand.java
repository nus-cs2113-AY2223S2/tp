package seedu.todolist.logic.command;

import seedu.todolist.exception.InvalidEditException;
import seedu.todolist.exception.InvalidIndexException;
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
        index = ParserUtil.parseIndex(args.get(Flags.COMMAND_EDIT));
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
    public void execute(TaskList taskList, Ui ui) throws InvalidIndexException {
        if (description != null) {
            taskList.getTask(index).setDescription(description);
        }
        if (deadline != null) {
            taskList.getTask(index).setDeadline(deadline);
        }
        if (email != null) {
            taskList.getTask(index).setEmail(email);
        }
        if (!tags.isEmpty()) {
            taskList.addTags(index, tags);
        }
        ui.printEditTaskMessage(taskList.getTask(index).toString());
    }
}
