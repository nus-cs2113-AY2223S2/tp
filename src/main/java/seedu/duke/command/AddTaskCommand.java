package seedu.duke.command;

import seedu.duke.exception.InvalidDescriptionException;
import seedu.duke.exception.InvalidTimeException;
import seedu.duke.exception.ToDoListException;
import seedu.duke.ui.Ui;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class AddTaskCommand extends Command {
    public static final String KEYWORD = "add";
    public static final HashSet<String> FLAGS = new HashSet<>(Arrays.asList(KEYWORD, ArgumentList.DEADLINE_FLAG));

    private String description;
    private String deadline;

    public AddTaskCommand(HashMap<String, String> args) throws ToDoListException {
        description = args.get(KEYWORD);
        if (description.isEmpty()) {
            throw new InvalidDescriptionException();
        }

        try {
            deadline = CommandParser.formatDateTime(args.get(ArgumentList.DEADLINE_FLAG));
        } catch (DateTimeParseException e) {
            throw new InvalidTimeException();
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui){
        String taskString = taskList.addTask(new Task(description, deadline));
        ui.printAddTaskNotification(taskString);
    }
}
