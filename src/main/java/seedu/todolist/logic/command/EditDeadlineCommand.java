package seedu.todolist.logic.command;

import seedu.todolist.exception.InvalidIndexException;
import seedu.todolist.exception.InvalidTimeException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.constants.Flags;
import seedu.todolist.logic.Parser;
import seedu.todolist.ui.Ui;
import seedu.todolist.task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

//@@author clement559
public class EditDeadlineCommand extends Command  {
    public static final String KEYWORD = "edit";
    public static final HashSet<String> FLAGS = new HashSet<>(Arrays.asList(KEYWORD, Flags.DEADLINE.FLAG));

    public int index;
    public LocalDateTime deadline;

    public EditDeadlineCommand(HashMap<String, String> args) throws ToDoListException {
        try {
            index = Integer.parseInt(args.get(KEYWORD)) - 1;
            deadline = Parser.formatDateTime(args.get(Flags.DEADLINE.FLAG));
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        } catch (DateTimeParseException e) {
            throw new InvalidTimeException();
        }
        assert index >= 0 : "Invalid index contained in variable";
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIndexException {
        String taskString = taskList.editDeadline(index, deadline);
        assert !taskString.isEmpty() : "Conversion of task string failed";
        ui.printEditTaskMessage(taskString);
    }
}
