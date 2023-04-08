package seedu.parser;

import seedu.commands.Command;
import seedu.commands.ExitCommand;
import seedu.commands.caloriecommands.HelpCaloriesCommand;
import seedu.commands.errorcommands.InvalidCommand;
import seedu.commands.workoutcommands.HelpWorkoutCommand;
import seedu.exceptions.InvalidArgumentException;
import seedu.exceptions.InvalidSyntaxException;

import java.text.ParseException;
import java.util.Date;
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
        case "/whelp":
            return new HelpWorkoutCommand();
        case "/cadd":
            return CalorieParser.parseAddCalorieCommand(arguments);
        case "/clist":
            return CalorieParser.parseListCalorieCommand(arguments);
        case "/cview":
            return CalorieParser.parseViewCaloriesCommand(arguments);
        case "/cdelete":
            //delete calories for a specific day for one food follow /cdelete date food name
            break;
        case "/chelp":
            return new HelpCaloriesCommand();
        case "/exit":
            return new ExitCommand();
        default:
            return new InvalidCommand(commandName);
        }
        return new InvalidCommand(commandName);
    }

    /**
     * This method is used to check the input date format
     *
     * @param arguments inputs date
     * @return return null if the date format is invalid
     */
    //@@ author ZIZI-czh
    static Date parseDate(String arguments) throws InvalidSyntaxException, InvalidArgumentException {
        arguments = arguments.trim();
        Date enteredDate;
        try {
            enteredDate = DateFormatter.stringToDate(arguments);
        } catch (ParseException e) {
            throw new InvalidSyntaxException("date");
        }
        Date currentDate = new Date();
        if (enteredDate.compareTo(currentDate) > 0) {
            throw new InvalidArgumentException("date");
        }
        return enteredDate;
    }
}

