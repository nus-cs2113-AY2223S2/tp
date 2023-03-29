//@@author RuiShengGit
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidEditException;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;

public class EditEmailCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_EDIT_EMAIL, Flags.EDIT, Flags.EDIT_DELETE};

    private int id;
    private String email;

    public EditEmailCommand(HashMap<Flags, String> args) throws ToDoListException {
        id = ParserUtil.parseId(args.get(Flags.COMMAND_EDIT_EMAIL));
        if (args.containsKey(Flags.EDIT)) {
            email = ParserUtil.parseEmail(args.get(Flags.EDIT));
        } else if (!args.containsKey(Flags.EDIT_DELETE)) {
            throw new InvalidEditException();
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIdException {
        String taskString = taskList.setEmail(id, email);
        if (email == null) {
            ui.printEditDeleteTaskMessage("email address", taskString);
        } else {
            ui.printEditTaskMessage("email address", email, taskString);
        }
    }
}
