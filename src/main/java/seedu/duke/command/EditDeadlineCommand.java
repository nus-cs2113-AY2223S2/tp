package seedu.duke.command;

import seedu.duke.exception.InvalidIndexException;
import seedu.duke.exception.InvalidTimeException;
import seedu.duke.exception.ToDoListException;
import seedu.duke.ui.Ui;
import seedu.duke.task.TaskList;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class EditDeadlineCommand extends Command  {
    public static final String KEYWORD = "edit";
    public static final HashSet<String> FLAGS = new HashSet<>(Arrays.asList(KEYWORD, ArgumentList.DEADLINE_FLAG));

    public int index;
    public String deadline;

    public EditDeadlineCommand(HashMap<String, String> args) throws ToDoListException {
        try {
            index = Integer.parseInt(args.get(KEYWORD)) - 1;
            deadline = CommandParser.formatDateTime(args.get(ArgumentList.DEADLINE_FLAG));
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        } catch (DateTimeParseException e) {
            throw new InvalidTimeException();
        }
        assert index >= 0 : "Invalid index contained in variable";
        assert !deadline.isEmpty() : "Empty deadline contained in variable";
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIndexException {
        String taskString = taskList.editDeadline(index, deadline);
        assert !taskString.isEmpty() : "Conversion of task string failed";
        ui.printEditDeadlineNotification(taskString);
    }
}
