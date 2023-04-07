package seedu.todolist.logic.command;
//@@author KedrianLoh
import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidFindException;
import seedu.todolist.exception.InvalidPriorityException;
import seedu.todolist.logic.ParserUtil;
import seedu.todolist.task.Task;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

public class FindByPriorityCommand extends Command{
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_FIND_PRIORITY};

    private int priority;

    public FindByPriorityCommand(HashMap<Flags, String> args) throws InvalidPriorityException {
        if (args.containsKey(Flags.COMMAND_FIND_PRIORITY)) {
            priority = ParserUtil.parsePriority(args.get(Flags.COMMAND_FIND_PRIORITY));
        } else {
            throw new InvalidPriorityException(args.get(Flags.COMMAND_FIND_PRIORITY));
        }
    }
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidFindException {
        if (taskList.getAllPrioritiesInTaskList().contains(priority)) {
            ArrayList<Task> arrayList = taskList.getTaskWithPriority(priority);
            ui.printTasksWithPriority(arrayList);
        } else {
            throw new InvalidFindException();
        }
    }
}
