//@@author RuiShengGit
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;
import java.util.HashSet;

public class MarkTaskCommand extends Command{
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_MARK};

    private HashSet<Integer> idHashSet;

    public MarkTaskCommand(HashMap<Flags, String> args) throws InvalidIdException {
        idHashSet = ParserUtil.parseId(args.get(Flags.COMMAND_MARK));
        // assert idHashSet >= 0 : "Invalid id contained in variable";
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIdException {
        for (int id : idHashSet) {
            String taskString = taskList.setDone(id, true);
            ui.printMarkTaskMessage(taskString);
        }
    }
}
