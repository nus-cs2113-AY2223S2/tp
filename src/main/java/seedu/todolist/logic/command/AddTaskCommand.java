package seedu.todolist.logic.command;

import seedu.todolist.exception.InvalidDescriptionException;
import seedu.todolist.exception.InvalidTimeException;
import seedu.todolist.exception.InvalidDurationException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.constants.Flags;
import seedu.todolist.logic.Parser;
import seedu.todolist.ui.Ui;
import seedu.todolist.task.Task;
import seedu.todolist.task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

//@@author jeromeongithub
public class AddTaskCommand extends Command {
    public static final String KEYWORD = "add";
    public static final HashSet<String> FLAGS = new HashSet<>(Arrays.asList(KEYWORD,
            Flags.DEADLINE.FLAG, Flags.REPEAT.FLAG));

    private String description;
    private LocalDateTime deadline;
    private int repeatDuration;

    public AddTaskCommand(HashMap<String, String> args) throws ToDoListException {
        description = args.get(KEYWORD);
        if (description.isEmpty()) {
            throw new InvalidDescriptionException();
        }

        try {
            deadline = Parser.formatDateTime(args.get(Flags.DEADLINE.FLAG));
        } catch (DateTimeParseException e) {
            throw new InvalidTimeException();
        }
        try {
            repeatDuration = Integer.parseInt(args.get(Flags.REPEAT.FLAG));
        } catch (NumberFormatException e) {
            throw new InvalidDurationException();
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        String taskString = taskList.addTask(new Task(description, deadline, repeatDuration));
        ui.printAddTaskMessage(taskString);
    }
}
