//@@author clement559
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidEditException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;

public class EditRepeatCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_EDIT_REPEAT, Flags.EDIT, Flags.EDIT_DELETE};

    private int id;
    private String repeatDurationString;

    public EditRepeatCommand(HashMap<Flags, String> args) throws ToDoListException {
        id = ParserUtil.parseId(args.get(Flags.COMMAND_EDIT_REPEAT));
        if (args.containsKey(Flags.EDIT)) {
            repeatDurationString = args.get(Flags.EDIT);
        } else if (!args.containsKey(Flags.EDIT_DELETE)) {
            throw new InvalidEditException();
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws ToDoListException {
        int repeatDuration = ParserUtil.parseRepeatDuration(repeatDurationString, taskList.getDeadline(id));
        String taskString = taskList.setRepeatDuration(id, repeatDuration);
        if (repeatDuration == 0) {
            ui.printEditDeleteTaskMessage("repeat duration", taskString);
        } else {
            ui.printEditTaskMessage("repeat duration", Integer.toString(repeatDuration), taskString);
        }
    }
}
