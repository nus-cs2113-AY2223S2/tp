package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidIndexException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;

//@@author RuiShengGit
public class GetEmailCommand extends Command{
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_GET_EMAIL};

    private int index;

    public GetEmailCommand(HashMap<Flags, String> args) throws InvalidIndexException {
        index = ParserUtil.parseIndex(args.get(Flags.COMMAND_GET_EMAIL));
        assert index >= 0 : "Invalid index contained in variable";
    }
    @Override
    public void execute(TaskList taskList, Ui ui) throws ToDoListException {
        String email = taskList.getTask(index).getEmail();
        ui.printGetTaskEmailMessage(email);
    }
}
