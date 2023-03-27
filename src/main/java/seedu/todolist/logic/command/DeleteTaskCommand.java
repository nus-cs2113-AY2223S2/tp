package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.ui.Ui;
import seedu.todolist.task.TaskList;

import java.util.HashMap;

//@@author RuiShengGit
public class DeleteTaskCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_DELETE};

    private int index;

    public DeleteTaskCommand(HashMap<Flags, String> args) throws InvalidIdException {
        index = ParserUtil.parseId(args.get(Flags.COMMAND_DELETE));
        assert index >= 0: "Invalid index contained in variable";
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIdException {
        String taskString = taskList.deleteTask(index);
        ui.printDeleteTaskMessage(taskString);
    }
}
