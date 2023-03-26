package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;

//@@author RuiShengGit
public class GetEmailCommand extends Command{
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_GET_EMAIL};

    private int index;

    public GetEmailCommand(HashMap<Flags, String> args) throws InvalidIdException {
        index = ParserUtil.parseId(args.get(Flags.COMMAND_GET_EMAIL));
        assert index >= 0 : "Invalid index contained in variable";
    }
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIdException {
        String email = taskList.getEmail(index);
        ui.printGetTaskEmailMessage(email);
    }
}
