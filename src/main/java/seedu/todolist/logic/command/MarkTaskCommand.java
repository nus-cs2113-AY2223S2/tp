package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidIndexException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.ui.Ui;
import seedu.todolist.task.TaskList;

import java.util.HashMap;

//@@author RuiShengGit
public class MarkTaskCommand extends Command{
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_MARK};

    private int index;

    public MarkTaskCommand(HashMap<Flags, String> args) throws InvalidIndexException {
        index = ParserUtil.parseIndex(args.get(Flags.COMMAND_MARK));
        assert index >= 0 : "Invalid index contained in variable";
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIndexException {
        taskList.getTask(index).setDone(true);
        ui.printMarkTaskMessage(taskList.getTask(index).toString());
    }
}
