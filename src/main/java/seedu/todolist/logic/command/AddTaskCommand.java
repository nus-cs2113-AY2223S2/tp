package seedu.todolist.logic.command;

import seedu.todolist.exception.ToDoListException;
import seedu.todolist.constants.Flags;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.ui.Ui;
import seedu.todolist.task.TaskList;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Command class that will add a Task object to the given TaskList object.
 */
public class AddTaskCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_ADD,
        Flags.EMAIL, Flags.DEADLINE, Flags.TAG, Flags.REPEAT};

    private String description;
    private String email;
    private LocalDateTime deadline;
    private HashSet<String> tags;
    private int repeatDuration;

    /**
     * Constructs an AddTaskCommand object by parsing the provided arguments.
     * Optional parameters (anything except the description) are allowed to be null.
     *
     * @param args The provided arguments, parsed from the user's input.
     * @throws ToDoListException If any of the provided arguments are invalid.
     */
    public AddTaskCommand(HashMap<Flags, String> args) throws ToDoListException {
        description = ParserUtil.parseDescription(args.get(Flags.COMMAND_ADD));
        deadline = ParserUtil.parseDeadline(args.get(Flags.DEADLINE));
        email = ParserUtil.parseEmail(args.get(Flags.EMAIL));
        tags = ParserUtil.parseTags(args.get(Flags.TAG));
        repeatDuration = ParserUtil.parseRepeatDuration(args.get(Flags.REPEAT), deadline);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        String taskString = taskList.addTask(description, deadline, email, tags, repeatDuration);
        ui.printAddTaskMessage(taskString);
    }
}
