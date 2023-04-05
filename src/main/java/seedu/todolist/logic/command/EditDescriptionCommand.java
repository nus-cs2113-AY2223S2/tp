package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;

public class EditDescriptionCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_EDIT_DESCRIPTION, Flags.EDIT};

    private int id;
    private String description;

    public EditDescriptionCommand(HashMap<Flags, String> args) throws ToDoListException {
        id = ParserUtil.parseId(args.get(Flags.COMMAND_EDIT_DESCRIPTION));
        description = args.get(Flags.EDIT);
        assert description != null && !description.isEmpty(): "Missing description uncaught by parser!";
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIdException {
        String taskString = taskList.setDescription(id, description);
        ui.printEditTaskMessage("description", description, taskString);
    }
}
