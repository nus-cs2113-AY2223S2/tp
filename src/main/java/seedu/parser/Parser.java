package seedu.parser;

import seedu.commands.Command;
import seedu.commands.ListCommand;
import seedu.commands.StartCommand;
import seedu.commands.AddCommand;
import seedu.commands.DeleteCommand;
import seedu.commands.ExitCommand;
import seedu.commands.EndCommand;
import seedu.commands.IncorrectCommand;
import seedu.workout.Exercise;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {


    private static final int EXERCISE_NAME_INDEX = 0;
    private static final int WEIGHT_INDEX = 1;
    private static final int REPS_PER_SET_INDEX = 2;
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandName>\\S+)(?<arguments>" +
            ".*)");

    public Command processCommand(String userInput) {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand();
        }

        String commandName = matcher.group("commandName");
        String arguments = matcher.group("arguments");

        switch (commandName) {
        case "/start":
            return processStart(arguments);
        case "/add":
            return processAdd(arguments);
        case "/delete":
            return processDelete(arguments);
        case "/list":
            if (arguments != null && !arguments.trim().isEmpty()) {
                // If there are arguments after the "/list" command, return an IncorrectCommand instance
                return new IncorrectCommand();
            }
            return new ListCommand();

        case "/end":
            return new EndCommand();
        case "/exit":
            return new ExitCommand();
        default:
            return new IncorrectCommand();
        }
    }

    private Command processStart(String arguments) {
        Date date;
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            date = dateFormat.parse(arguments);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format dd/mm/yy.");
            return new IncorrectCommand();
        }
        return new StartCommand(date);
    }

    private Command processAdd(String arguments) {
        Exercise toAdd;
        try {
            String[] exerciseDetails = arguments.split("/", 3);
            String exerciseName = exerciseDetails[EXERCISE_NAME_INDEX].trim();
            String weight = exerciseDetails[WEIGHT_INDEX].replace("weight", "").trim();
            String repsPerSet =
                    exerciseDetails[REPS_PER_SET_INDEX].replace("rps", "").replace("rps", "").trim();
            toAdd = new Exercise(exerciseName, weight, repsPerSet);

        } catch (ArrayIndexOutOfBoundsException e) {
            return new IncorrectCommand();
        }
        return new AddCommand(toAdd);
    }

    private Command processDelete(String arguments) {
        Date date;
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            date = dateFormat.parse(arguments);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format dd/mm/yy.");
            return new IncorrectCommand();
        }

        return new DeleteCommand(date);
    }

}
