package seedu.parser;

import seedu.commands.Command;
import seedu.commands.AddMealCommand;
import seedu.commands.DeleteCommand;
import seedu.commands.ExitCommand;
import seedu.commands.UpdateUserCommand;
import seedu.commands.ViewUserCommand;
import seedu.commands.ListCommand;
import seedu.commands.FilterCaloriesCommand;
import seedu.commands.HelpCommand;
import seedu.commands.NutritionCommand;
import seedu.commands.AddExerciseCommand;
import seedu.commands.TrackCalorieCommand;
import seedu.commands.ExamplesCommand;
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
            command = new DeleteCommand(commandWord, userInput);
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
            command = new FilterCaloriesCommand(commandWord, userInput);
            break;
        case "nutrition":
            command = new NutritionCommand();
            break;
        case "exercise":
            command = new AddExerciseCommand(commandWord, userInput);
            break;
        case "track":
            command = new TrackCalorieCommand(userInput);
            break;
        case "help":
            command = new HelpCommand();
            break;
        case "examples":
            command = new ExamplesCommand(commandWord, userInput);
            break;
        default:
            throw new InvalidCommandException();
        }
        return command;
    }
}
