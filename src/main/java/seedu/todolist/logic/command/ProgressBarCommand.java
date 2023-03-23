package seedu.todolist.logic.command;

import seedu.todolist.constants.Flags;
import seedu.todolist.exception.InvalidIndexException;
import seedu.todolist.exception.InvalidTimeException;
import seedu.todolist.exception.ToDoListException;
import seedu.todolist.logic.Parser;
import seedu.todolist.task.TaskList;
import seedu.todolist.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class ProgressBarCommand extends Command {
    public static final String KEYWORD = "progress";

    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidIndexException {

        // get current date
        LocalDateTime currentDateTime = LocalDateTime.now();

        // make monday and sunday

        // take the subset of tasks that are within the week of current date (is before and after || isequal || isequal)


        // find number of marked tasks

        // find number of unmarked tasks

    }
}
