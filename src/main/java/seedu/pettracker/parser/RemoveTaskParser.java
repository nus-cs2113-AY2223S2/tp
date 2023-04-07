package seedu.pettracker.parser;

import seedu.pettracker.commands.RemoveTaskCommand;
import seedu.pettracker.exceptions.EmptyArgException;
import seedu.pettracker.exceptions.IllegalArgException;

public class RemoveTaskParser implements ArgParser<RemoveTaskCommand>{
    final String EMPTY_ARG_MESSAGE = "This command requires arguments.";
    final String INVALID_TASK_NUMBER_MESSAGE = "Invalid task number. Please enter a valid task number.";
    @Override
    public RemoveTaskCommand parse(String commandArgs) throws IllegalArgException {
        if (commandArgs.isEmpty()) {
            throw new EmptyArgException(EMPTY_ARG_MESSAGE);
        }
        int taskNum;
        try {
            taskNum = Integer.parseInt(commandArgs);
        } catch (NumberFormatException e) {
            throw new IllegalArgException(INVALID_TASK_NUMBER_MESSAGE);
        }
        return new RemoveTaskCommand(taskNum);
    }
}
