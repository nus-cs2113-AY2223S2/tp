package seedu.todolist.logic.command;

//@@author RuiShengGit

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidFlagException;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;
import java.util.HashSet;

public class ListFullInfoCommand extends Command{
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_FULL_INFO};

    private HashSet<Integer> idHashSet;

    public ListFullInfoCommand(HashMap<Flags, String> args) throws InvalidIdException, InvalidFlagException {
        idHashSet = ParserUtil.parseId(args.get(Flags.COMMAND_FULL_INFO));
        // assert id >= 0 : "Invalid id contained in variable";
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIdException {
        for (int id : idHashSet) {
            ui.printGetFullInfoMessage(taskList.getFullInfo(id));
        }
    }
}
