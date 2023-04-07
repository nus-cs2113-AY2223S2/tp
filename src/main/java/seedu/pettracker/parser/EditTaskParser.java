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
    final String DATE_SEPARATOR = "/by";
    final String ARG_SEPARATOR = " ";
    @Override
    public EditTaskCommand parse(String commandArgs) throws IllegalArgException {
        if (commandArgs.isEmpty()) {
            throw new EmptyArgException(EMPTY_ARG_MESSAGE);
        }
        try {
            String[] split = commandArgs.split(ARG_SEPARATOR, 2);
            int taskNum;
            String description;
            try {
                taskNum = Integer.parseInt(split[0]);
                if (commandArgs.contains(DATE_SEPARATOR)) {
                    String[] timeSplit = split[1].split(DATE_SEPARATOR);
                    description = timeSplit[0].trim();
                    LocalDate deadline = LocalDate.parse(timeSplit[1].trim());
                    return new EditTaskCommand(taskNum, description, deadline);
                }
                description = split[1].trim();
                return new EditTaskCommand(taskNum, description);
            } catch (NumberFormatException e) {
                throw new IllegalArgException(INVALID_TASK_NUMBER_MESSAGE);
            } catch (DateTimeException e) {
                throw new IllegalArgException(INVALID_DATE_FORMAT_MESSAGE);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyArgException(INVALID_ARG_FORMAT_MESSAGE);
        }
    }
}
