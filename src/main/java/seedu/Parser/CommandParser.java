package seedu.Parser;

import seedu.Commands.AddFoodCommand;
import seedu.Commands.Command;
import seedu.Commands.ExitCommand;
import seedu.Commands.UpdateUserCommand;
import seedu.Exceptions.InvalidCommandException;
import seedu.Exceptions.LifeTrackerException;

public class CommandParser {
    public static Command parse(String userInput) throws LifeTrackerException {
        String[] userInputArray = userInput.split(" ");
        String commandWord = userInputArray[0];
        String commandDescriptor = userInput.substring(commandWord.length()).trim();
        Command command;

        switch(commandWord) {
        case "add":
            command = new AddFoodCommand(commandDescriptor);
            break;
        case "bye":
            command = new ExitCommand();
            break;
        case "update":
            command = new UpdateUserCommand();
            break;
        default:
            throw new InvalidCommandException();
        }
        return command;
    }
}
