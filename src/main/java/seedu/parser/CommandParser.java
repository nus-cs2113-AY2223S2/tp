package seedu.parser;

import seedu.commands.*;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.LifeTrackerException;

public class CommandParser {
    public static Command parse(String userInput) throws LifeTrackerException {
        String[] userInputArray = userInput.split(" ");
        String commandWord = userInputArray[0];
        Command command;

        switch(commandWord) {
        case "add":
            command = new AddMealCommand(commandWord, userInput);
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
        case "filter":
            command = new FilterCaloriesCommand();
            break;
        case "nutrition":
            command = new NutritionCommand();
            break;
        default:
            throw new InvalidCommandException();
        }
        return command;
    }
}
