package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;

//@@author RuiShengGit
public class ListFullInfoCommand extends Command{
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_FULL_INFO};

    private int id;

    public ListFullInfoCommand(HashMap<Flags, String> args) throws InvalidIdException {
        id = ParserUtil.parseId(args.get(Flags.COMMAND_FULL_INFO));
        assert id >= 0 : "Invalid id contained in variable";
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIdException {
        ui.printGetFullInfoMessage(taskList.getFullInfo(id));
    }
}
