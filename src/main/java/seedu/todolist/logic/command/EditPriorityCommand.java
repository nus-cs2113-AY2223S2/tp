//@@author RuiShengGit
package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.FormatterUtil;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;

public class EditPriorityCommand extends Command{
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_EDIT_PRIORITY, Flags.EDIT};

    private int id;
    private int priority;

    public EditPriorityCommand(HashMap<Flags, String> args) throws ToDoListException {
        id = ParserUtil.parseId(args.get(Flags.COMMAND_EDIT_PRIORITY));
        priority = ParserUtil.parsePriority(args.get(Flags.EDIT));
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws ToDoListException {
        String taskString = taskList.setPriority(id, priority);
        ui.printEditTaskMessage("priority level", FormatterUtil.getPriorityAsString(priority), taskString);
    }
}
