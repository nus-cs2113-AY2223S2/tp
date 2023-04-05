//@@author RuiShengGit
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.StringJoiner;

import static seedu.todolist.logic.ParserUtil.parseId;

public class DeleteTaskCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_DELETE};

    //@@author jeromeongithub
    private HashSet<Integer> idHashSet = new HashSet<Integer>();

    public DeleteTaskCommand(HashMap<Flags, String> args) throws InvalidIdException {
        String idList = args.get(Flags.COMMAND_DELETE);
        parseId(idList);
        String[] arrayOfIds = idList.split(" ");
        for (String idString : arrayOfIds) {
            int id = Integer.parseInt(idString);
            assert id >= 0 : "Invalid id contained in variable";
            idHashSet.add(id);
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIdException {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        for (int id : idHashSet) {
            String taskString = taskList.deleteTask(id);
            stringJoiner.add(taskString);
        }
        ui.printDeleteTaskMessage(stringJoiner.toString());
    }
}
