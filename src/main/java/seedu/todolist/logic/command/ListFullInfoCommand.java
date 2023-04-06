package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.model.Config;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;
import java.util.HashSet;

public class ListFullInfoCommand extends Command{
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_FULL_INFO};

    private HashSet<Integer> idHashSet;

    public ListFullInfoCommand(HashMap<Flags, String> args) throws InvalidIdException {
        idHashSet = ParserUtil.parseId(args.get(Flags.COMMAND_FULL_INFO));
    }

    @Override
    public void execute(TaskList taskList, Config config, Ui ui) throws InvalidIdException {
        ui.printGetFullInfoMessage(taskList.getFullInfo(idHashSet));
    }
}
