//@@author RuiShengGit
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.logic.FormatterUtil;
import seedu.todolist.model.Priority;
import seedu.todolist.exception.InvalidEditException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.model.Config;
import seedu.todolist.model.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;
import java.util.HashSet;

public class EditPriorityCommand extends Command{
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_EDIT_PRIORITY, Flags.EDIT, Flags.EDIT_DELETE};

    private HashSet<Integer> idHashSet;
    private Priority priority = Priority.NONE;

    public EditPriorityCommand(HashMap<Flags, String> args) throws ToDoListException {
        idHashSet = ParserUtil.parseId(args.get(Flags.COMMAND_EDIT_PRIORITY));
        if (args.containsKey(Flags.EDIT) == args.containsKey(Flags.EDIT_DELETE)) {
            throw new InvalidEditException();
        } else if (args.containsKey(Flags.EDIT)) {
            priority = ParserUtil.parsePriority(args.get(Flags.EDIT));
        }
    }

    @Override
    public void execute(TaskList taskList, Config config, Ui ui) throws ToDoListException {
        String taskString = taskList.setPriority(idHashSet, priority);
        if (priority == Priority.NONE) {
            ui.printEditDeleteTaskMessage("priority level", taskString);
        } else {
            ui.printEditTaskMessage("priority level", priority.toString(), taskString);
        }
    }
}
