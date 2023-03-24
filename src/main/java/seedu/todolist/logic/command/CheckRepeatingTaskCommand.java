package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidIndexException;
import seedu.todolist.ui.Ui;
import seedu.todolist.task.TaskList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

//@@author clement559
public class CheckRepeatingTaskCommand extends Command {
    public static final String KEYWORD = "check";

    private int index;

    public CheckRepeatingTaskCommand() {}

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.checkRepeatingTasks();
        ui.printCheckRepeatingTaskMessage();
    }
}
