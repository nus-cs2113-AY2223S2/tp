//@@author RuiShengGit
package seedu.todolist.logic.command;


import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidFlagException;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.StringJoiner;

public class UnmarkTaskCommand extends Command{
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_UNMARK};

    private HashSet<Integer> idHashSet;

    public UnmarkTaskCommand(HashMap<Flags, String> args) throws InvalidIdException, InvalidFlagException {
        idHashSet = ParserUtil.parseId(args.get(Flags.COMMAND_UNMARK));
        // assert id >= 0 : "Invalid id contained in variable";
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIdException {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        for (int id : idHashSet) {
            String taskString = taskList.setDone(id, false);
            stringJoiner.add(taskString);
        }
        ui.printUnmarkTaskMessage(stringJoiner.toString());
    }
}
