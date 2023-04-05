package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.model.Config;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;
import java.util.HashSet;

public class EditDescriptionCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_EDIT_DESCRIPTION, Flags.EDIT};

    private HashSet<Integer> idHashSet;
    private String description;

    // probably need to read a few descriptions instead of just one
    public EditDescriptionCommand(HashMap<Flags, String> args) throws ToDoListException {
        idHashSet = ParserUtil.parseId(args.get(Flags.COMMAND_EDIT_DESCRIPTION));
        description = args.get(Flags.EDIT);
        assert description != null && !description.isEmpty(): "Missing description uncaught by parser!";
    }

    @Override
    public void execute(TaskList taskList, Config config, Ui ui) throws InvalidIdException {
        for (int id: idHashSet) {
            String taskString = taskList.setDescription(id, description);
            ui.printEditTaskMessage("description", description, taskString);
        }
    }
}
