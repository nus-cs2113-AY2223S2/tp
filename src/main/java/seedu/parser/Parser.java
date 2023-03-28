package seedu.parser;

import seedu.commands.Command;
import seedu.commands.InvalidCommand;
import seedu.commands.workoutcommands.EndWorkoutCommand;
import seedu.commands.ExitCommand;
import seedu.commands.workoutcommands.HelpWorkoutCommand;
import seedu.exceptions.InvalidSyntaxException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandName>\\S+)(?<arguments>" +
            ".*)");

    public Command processCommand(String userInput) throws InvalidSyntaxException {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new InvalidSyntaxException("user input");
        }

        String commandName = matcher.group("commandName");
        String arguments = matcher.group("arguments");

        switch (commandName) {
        case "/start":
            return CheckInputs.processStart(arguments);
        case "/add":
            return CheckInputs.processAdd(arguments);
        case "/delete":
            return CheckInputs.processDelete(arguments);
        case "/list":
            return CheckInputs.processList(arguments);
        case "/view":
            return CheckInputs.processView(arguments);
        case "/end":
            return new EndWorkoutCommand();
        case "/exit":
            return new ExitCommand();
        case "/help":
            return new HelpWorkoutCommand();
        case "/cadd":
            return CheckCaloriesInput.processAddCalories(arguments);
        case "/cview":
            return CheckCaloriesInput.processViewCalories(arguments);
        default:
            return new InvalidCommand(commandName);
        }
    }
}

