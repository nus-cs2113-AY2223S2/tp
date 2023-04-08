package seedu.parser;

import seedu.commands.Command;
import seedu.commands.ExitCommand;
import seedu.commands.errorcommands.InvalidCommand;
import seedu.commands.HelpCommand;
import seedu.exceptions.InvalidArgumentException;
import seedu.exceptions.InvalidSyntaxException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@@author calebcjl
/**
 * Represents the main parser that parses user commands.
 */
public class Parser {
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandName>\\S+)(?<arguments>.*)");

    public static Command processCommand(String userInput) throws InvalidSyntaxException, InvalidArgumentException {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new InvalidSyntaxException("user input");
        }

        String commandName = matcher.group("commandName");
        String arguments = matcher.group("arguments");

        switch (commandName.toLowerCase()) {
        case "/wstart":
            return WorkoutParser.parseStartWorkoutCommand(arguments);
        case "/wadd":
            return WorkoutParser.parseAddExerciseCommand(arguments);
        case "/wdelete":
            return WorkoutParser.parseDeleteWorkoutCommand(arguments);
        case "/wlist":
            return WorkoutParser.parseListWorkoutCommand(arguments);
        case "/wview":
            return WorkoutParser.parseViewWorkoutCommand(arguments);
        case "/wcount":
            return WorkoutParser.parseSetsRepsCountCommand(arguments);
        case "/wend":
            return WorkoutParser.parseEndWorkoutCommand(arguments);
        case "/cadd":
            return CalorieParser.parseAddCalorieCommand(arguments);
        case "/clist":
            //list the total daily calories consumption
            break;
        case "/cview":
            // list all the food calories that been entered for a day
            break;
        case "/cdelete":
            //delete calories for a specific day for one food follow /cdelete date food name
            break;
        case "/exit":
            return new ExitCommand();
        case "/help":
            return new HelpCommand();
        default:
            return new InvalidCommand(commandName);
        }
        return new InvalidCommand(commandName);
    }

}

