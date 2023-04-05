//@@author RuiShengGit
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidEditException;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.model.Config;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;
import java.util.HashSet;

public class EditEmailCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_EDIT_EMAIL, Flags.EDIT, Flags.EDIT_DELETE};

    private HashSet<Integer> idHashSet;
    private String email;

    // probably need to read a bunch of emails too
    public EditEmailCommand(HashMap<Flags, String> args) throws ToDoListException {
        idHashSet = ParserUtil.parseId(args.get(Flags.COMMAND_EDIT_EMAIL));
        if (args.containsKey(Flags.EDIT)) {
            email = ParserUtil.parseEmail(args.get(Flags.EDIT));
        } else if (!args.containsKey(Flags.EDIT_DELETE)) {
            throw new InvalidEditException();
        }
    }

    @Override
    public void execute(TaskList taskList, Config config, Ui ui) throws InvalidIdException {
        for (int id : idHashSet) {
            String taskString = taskList.setEmail(id, email);
            if (email == null) {
                ui.printEditDeleteTaskMessage("email address", taskString);
            } else {
                ui.printEditTaskMessage("email address", email, taskString);
            }
        }
    }
}
