package seedu.pettracker.parser;


import seedu.pettracker.commands.EditTaskCommand;
import seedu.pettracker.exceptions.EmptyArgException;
import seedu.pettracker.exceptions.IllegalArgException;

import java.time.DateTimeException;
import java.time.LocalDate;

public class EditTaskParser implements ArgParser<EditTaskCommand>{
    final String EMPTY_ARG_MESSAGE = "This command requires arguments.";
    final String INVALID_ARG_FORMAT_MESSAGE = "Invalid argument format. Please enter the arguments in the " +
            "following format: TASK_NUMBER DESCRIPTION DEADLINE.";
    final String INVALID_TASK_NUMBER_MESSAGE = "Invalid task number. Please enter a valid task number.";
    final String INVALID_DATE_FORMAT_MESSAGE = "Invalid date format. Please enter the date in the following format: " +
            "YYYY-MM-DD.";
    @Override
    public EditTaskCommand parse(String commandArgs) throws IllegalArgException {
        if (commandArgs.isEmpty()) {
            throw new EmptyArgException(EMPTY_ARG_MESSAGE);
        }
        try {
            String[] split = commandArgs.split(" ", 2);
            String[] timeSplit = split[1].split("(?i) /by ");
            int taskNum;
            String description;
            LocalDate deadline = null;
            try {
                taskNum = Integer.parseInt(split[0]);
                description = timeSplit[0];
                if (timeSplit.length > 1) {
                    deadline = LocalDate.parse(timeSplit[1]);
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgException(INVALID_TASK_NUMBER_MESSAGE);
            } catch (DateTimeException e) {
                throw new IllegalArgException(INVALID_DATE_FORMAT_MESSAGE);
            }
            if (timeSplit.length > 1) {
                return new EditTaskCommand(taskNum, description, deadline);
            } else {
                return new EditTaskCommand(taskNum, description);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyArgException(INVALID_ARG_FORMAT_MESSAGE);
        }
    }
}
