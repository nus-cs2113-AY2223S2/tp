package seedu.Parser;

import seedu.Commands.AddMealCommand;
import seedu.Commands.UpdateUserCommand;
import seedu.Commands.ViewUserCommand;
import seedu.Commands.Command;
import seedu.Commands.DeleteMealCommand;
import seedu.Commands.ExitCommand;
import seedu.Commands.ListCommand;
import seedu.Exceptions.InvalidCommandException;
import seedu.Exceptions.LifeTrackerException;

public class CommandParser {
    public static Command parse(String userInput) throws LifeTrackerException {
        String[] userInputArray = userInput.split(" ");
        String commandWord = userInputArray[0];
        Command command;

        switch(commandWord) {
        case "add":
            command = new AddMealCommand();
            break;
        case "delete":
            command = new DeleteMealCommand(commandWord, userInput);
            break;
        case "bye":
            command = new ExitCommand();
            break;
        case "update":
            command = new UpdateUserCommand();
            break;
        case "view":
            command = new ViewUserCommand();
            break;
        case "list":
            command = new ListCommand(commandWord, userInput);
            break;
        default:
            throw new InvalidCommandException();
        }
        return command;
    }
}
