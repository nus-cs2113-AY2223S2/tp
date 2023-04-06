//@@author RuiShengGit
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidEditException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.FormatterUtil;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.StringJoiner;

public class EditPriorityCommand extends Command{
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_EDIT_PRIORITY, Flags.EDIT, Flags.EDIT_DELETE};

    private HashSet<Integer> idHashSet;
    private int priority;

    public EditPriorityCommand(HashMap<Flags, String> args) throws ToDoListException {
        idHashSet = ParserUtil.parseId(args.get(Flags.COMMAND_EDIT_PRIORITY));
        if (args.containsKey(Flags.EDIT)) {
            priority = ParserUtil.parsePriority(args.get(Flags.EDIT));
        } else if (!args.containsKey(Flags.EDIT_DELETE)) {
            throw new InvalidEditException();
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws ToDoListException {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        for (int id : idHashSet) {
            String taskString = taskList.setPriority(id, priority);
            stringJoiner.add(taskString);
        }
        if (priority == 0) {
            ui.printEditDeleteTaskMessage("priority level", stringJoiner.toString());
        } else {
            ui.printEditTaskMessage("priority level", FormatterUtil.getPriorityAsString(priority),
                    stringJoiner.toString());
        }
    }
}
