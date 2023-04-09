package seedu.pettracker.parser;

import seedu.pettracker.commands.AddTaskCommand;
import seedu.pettracker.exceptions.EmptyArgException;
import seedu.pettracker.exceptions.IllegalArgException;

import java.time.DateTimeException;
import java.time.LocalDate;

public class AddTaskParser implements ArgParser<AddTaskCommand>{
    final String EMPTY_ARG_MESSAGE = "This command requires arguments.";
    final String EMPTY_DESC_MESSAGE = "Please enter a description for the task.";
    final String INVALID_ARG_FORMAT_MESSAGE = "Invalid argument format. Please enter the arguments in the " +
            "following format: DESCRIPTION /by DEADLINE.";
    final String INVALID_DATE_FORMAT_MESSAGE = "Invalid date format. Please enter the date in the following format: " +
            "YYYY-MM-DD. Also, ensure that your arguments are in the following format: DESCRIPTION /by DEADLINE";
    final String DATE_SEPARATOR = " */by *";
    @Override
    public AddTaskCommand parse(String commandArgs) throws IllegalArgException {
        if (commandArgs.isEmpty()) {
            throw new EmptyArgException(EMPTY_DESC_MESSAGE);
        }
        try {
            String[] args = commandArgs.split(DATE_SEPARATOR);
            String description = args[0].trim();
            if (description.isEmpty()) {
                throw new EmptyArgException(EMPTY_ARG_MESSAGE);
            }
            LocalDate deadline;
            if (commandArgs.contains(DATE_SEPARATOR)) {
                deadline = LocalDate.parse(args[1].trim());
                return new AddTaskCommand(description, deadline);
            }
            return new AddTaskCommand(description);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyArgException(INVALID_ARG_FORMAT_MESSAGE);
        } catch (DateTimeException e) {
            throw new IllegalArgException(INVALID_DATE_FORMAT_MESSAGE);
        }
    }
}
