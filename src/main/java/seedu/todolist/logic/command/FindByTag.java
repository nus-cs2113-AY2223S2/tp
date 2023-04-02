package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidFindException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.task.Task;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

public class FindByTag extends Command{
    public static final Flags[] EXPECTED_FLAGS = {Flags.COMMAND_FIND_TAG};

    private String tag;

    public FindByTag(HashMap<Flags, String> args) throws ToDoListException {
        if (args.containsKey(Flags.COMMAND_FIND_TAG)) {
            tag = args.get(Flags.COMMAND_FIND_TAG);
        } else {
            throw new InvalidFindException();
        }
    }
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidFindException {
        if (taskList.getAllTags().contains(tag)) {
            ArrayList<Task> arrayList = taskList.getTaskWithTag(tag);
            ui.printTasksWithTag(arrayList);
        } else {
            throw new InvalidFindException();
        }
    }
}
