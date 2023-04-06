//@@author clement559
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidEditException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.FormatterUtil;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.model.Config;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;

public class EditRepeatCommand extends Command {
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_EDIT_REPEAT, Flags.EDIT, Flags.EDIT_DELETE};

    private HashSet<Integer> idHashSet;
    private String repeatDurationString;

    public EditRepeatCommand(HashMap<Flags, String> args) throws ToDoListException {
        idHashSet = ParserUtil.parseId(args.get(Flags.COMMAND_EDIT_REPEAT));
        if (args.containsKey(Flags.EDIT) == args.containsKey(Flags.EDIT_DELETE)) {
            throw new InvalidEditException();
        } else if (args.containsKey(Flags.EDIT)) {
            repeatDurationString = args.get(Flags.EDIT);
        }
    }

    @Override
    public void execute(TaskList taskList, Config config, Ui ui) throws ToDoListException {
        int repeatDuration = ParserUtil.parseRepeatDuration(repeatDurationString, LocalDateTime.MIN);
        String taskString = taskList.setRepeatDuration(idHashSet, repeatDuration);
        if (repeatDuration == 0) {
            ui.printEditDeleteTaskMessage("repeat times", taskString);
        } else {
            ui.printEditTaskMessage("repeat times", repeatDurationString, taskString);
        }
    }
}
