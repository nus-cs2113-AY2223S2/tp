package seedu.duke.command;

import seedu.duke.exception.InvalidIndexException;
import seedu.duke.ui.Ui;
import seedu.duke.task.TaskList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class DeleteTaskCommand extends Command {
    public static final String KEYWORD = "delete";
    public static final HashSet<String> FLAGS = new HashSet<>(Arrays.asList(KEYWORD));

    private int index;

    public DeleteTaskCommand(HashMap<String, String> args) throws InvalidIndexException {
        try {
            index = Integer.parseInt(args.get(KEYWORD)) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIndexException {
        String taskString = taskList.deleteTask(index);
        ui.printDeleteTaskNotification(taskString);
    }
}
