package seedu.todolist.logic.command;


import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.StringJoiner;

public class EditDescriptionCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_EDIT_DESCRIPTION, Flags.EDIT};

    private HashSet<Integer> idHashSet;
    private String description;

    // probably need to read a few descriptions instead of just one
    //@@author jeromeongithub
    public EditDescriptionCommand(HashMap<Flags, String> args) throws ToDoListException {
        idHashSet = ParserUtil.parseId(args.get(Flags.COMMAND_EDIT_DESCRIPTION));
        description = args.get(Flags.EDIT);
        assert description != null && !description.isEmpty(): "Missing description uncaught by parser!";
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIdException {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        for (int id: idHashSet) {
            String taskString = taskList.setDescription(id, description);
            stringJoiner.add(taskString);
        }
        ui.printEditTaskMessage("description", description, stringJoiner.toString());
    }
}
