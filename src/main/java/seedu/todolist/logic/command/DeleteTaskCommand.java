//@@author RuiShengGit
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidIdException;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;

public class DeleteTaskCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_DELETE};

    // private int id;
    private ArrayList<Integer> idArrayList = new ArrayList<Integer>();

    public DeleteTaskCommand(HashMap<Flags, String> args) throws InvalidIdException {
        String idList = args.get(Flags.COMMAND_DELETE);
        String[] arrayOfIds = idList.split(" ");
        for (String idString: arrayOfIds) {
            int id = Integer.parseInt(idString);
            assert id >= 0: "Invalid id contained in variable";
            idArrayList.add(id);
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIdException {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        for (int id : idArrayList) {
            String taskString = taskList.deleteTask(id);
            stringJoiner.add(taskString);
        }
        ui.printDeleteTaskMessage(stringJoiner.toString());
    }
}
