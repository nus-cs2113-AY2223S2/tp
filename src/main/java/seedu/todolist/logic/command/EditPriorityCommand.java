package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidEditException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.HashMap;

public class EditPriorityCommand extends Command{
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_EDIT_PRIORITY, Flags.EDIT, Flags.EDIT_DELETE};
    private int id;
    private String priorityString;

    public EditPriorityCommand(HashMap<Flags, String> args) throws ToDoListException {
        id = ParserUtil.parseId(args.get(Flags.COMMAND_EDIT_PRIORITY));
        if (args.containsKey(Flags.EDIT)) {
            priorityString = args.get(Flags.EDIT);
        } else if (!args.containsKey(Flags.EDIT_DELETE)) {
            throw new InvalidEditException();
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws ToDoListException {
        int priority = ParserUtil.parsePriority(priorityString);
        String taskString = taskList.setPriority(id, priority);
        String priorityLevel;
        if (priority == 0) {
            ui.printEditDeleteTaskMessage("priority level", taskString);
        } else {
            if (priority == 1) {
                priorityLevel = "Low";
            } else if (priority == 2) {
                priorityLevel = "Medium";
            } else {
                priorityLevel = "High";
            }
            ui.printEditTaskMessage("priority level", priorityLevel, taskString);
        }
    }
}
