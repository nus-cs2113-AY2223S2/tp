package seedu.todolist.logic.command;

import seedu.todolist.exception.ToDoListException;
import seedu.todolist.constants.Flags;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.model.Config;
import seedu.todolist.ui.Ui;
import seedu.todolist.model.TaskList;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * Command class that will add a Task object to the given TaskList object.
 */
public class AddTaskCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_ADD,
        Flags.EMAIL, Flags.DEADLINE, Flags.TAG, Flags.REPEAT, Flags.PRIORITY};

    private String description;
    private String email;
    private LocalDateTime deadline;
    private TreeSet<String> tags;
    private int repeatDuration;
    private int priority;

    /**
     * Constructs an AddTaskCommand object by parsing the provided arguments.
     * Optional parameters (anything except the description) are allowed to be null.
     *
     * @param args The provided arguments, parsed from the user's input.
     * @throws ToDoListException If any of the provided arguments are invalid.
     */
    public AddTaskCommand(HashMap<Flags, String> args) throws ToDoListException {
        description = args.get(Flags.COMMAND_ADD);
        deadline = ParserUtil.parseDeadline(args.get(Flags.DEADLINE));
        email = ParserUtil.parseEmail(args.get(Flags.EMAIL));
        tags = ParserUtil.parseTags(args.get(Flags.TAG));
        repeatDuration = ParserUtil.parseRepeatDuration(args.get(Flags.REPEAT), deadline);
        priority = ParserUtil.parsePriority(args.get(Flags.PRIORITY));
        assert description != null && !description.isEmpty(): "Missing description uncaught by parser!";
    }

    @Override
    public void execute(TaskList taskList, Config config, Ui ui) {
        String taskString = taskList.addTask(description, deadline, email, tags, repeatDuration, priority);
        ui.printAddTaskMessage(taskString);
    }
}
