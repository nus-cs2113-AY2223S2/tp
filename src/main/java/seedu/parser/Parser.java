package seedu.parser;


import seedu.commands.Command;
import seedu.commands.ExitCommand;
import seedu.commands.InvalidCommand;
import seedu.commands.workoutcommands.HelpWorkoutCommand;
import seedu.exceptions.InvalidSyntaxException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandName>\\S+)(?<arguments>" +
            ".*)");
    private boolean isDayEntered;

    public Command processCommand(String userInput) throws InvalidSyntaxException {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new InvalidSyntaxException("user input");
        }

        String commandName = matcher.group("commandName");
        String arguments = matcher.group("arguments");

        switch (commandName) {
        case "/day":
            Command.setIsDayEntered(true);
            Command.setIsWorkoutEntered(false);
            return CheckInputs.processDay(arguments);
        case "/start":
            Command.setIsWorkoutEntered(true);
            return CheckInputs.processStart(arguments);
        case "/wadd":
            return CheckInputs.processAdd(arguments);
        case "/delete":
            return CheckInputs.processDelete(arguments);
        case "/list":
            return CheckInputs.processList(arguments);
        case "/wview":
            return CheckInputs.processView(arguments);
        case "/count":
            return CheckInputs.processSetsRepsCount(arguments);
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

