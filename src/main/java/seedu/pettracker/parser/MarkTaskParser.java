package seedu.pettracker.parser;

import seedu.pettracker.commands.MarkTaskCommand;
import seedu.pettracker.exceptions.EmptyArgException;
import seedu.pettracker.exceptions.IllegalArgException;

public class MarkTaskParser implements ArgParser<MarkTaskCommand>{
    final String EMPTY_ARG_MESSAGE = "This command requires arguments.";
    final String INVALID_ARG_FORMAT_MESSAGE = "Invalid argument format. Please enter the arguments in the " +
            "following format: TASK_NUMBER.";
    final String INVALID_TASK_NUMBER_MESSAGE = "Invalid task number. Please enter a valid task number.";
    @Override
    public MarkTaskCommand parse(String commandArgs) throws IllegalArgException {
        if (commandArgs.isEmpty()) {
            throw new EmptyArgException(EMPTY_ARG_MESSAGE);
        }
        try {
            int taskNum;
            try {
                taskNum = Integer.parseInt(commandArgs);
            } catch (NumberFormatException e) {
                throw new IllegalArgException(INVALID_TASK_NUMBER_MESSAGE);
            }
            return new MarkTaskCommand(taskNum);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgException(INVALID_ARG_FORMAT_MESSAGE);
        }
    }
}
