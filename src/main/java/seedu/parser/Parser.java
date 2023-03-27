package seedu.parser;


import seedu.commands.Command;
import seedu.commands.caloriecommands.AddCalorieCommand;
import seedu.commands.workoutcommands.AddWorkoutCommand;
import seedu.commands.workoutcommands.DeleteWorkoutCommand;
import seedu.commands.workoutcommands.EndWorkoutCommand;
import seedu.commands.ExitCommand;
import seedu.commands.workoutcommands.HelpWorkoutCommand;
import seedu.commands.IncorrectCommand;
import seedu.commands.workoutcommands.ListWorkoutCommand;
import seedu.commands.workoutcommands.StartWorkoutCommand;
import seedu.commands.workoutcommands.ViewWorkoutCommand;
import seedu.workouttracker.workout.Exercise;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.commands.caloriecommands.AddCalorieCommand.CALORIES_NOT_GIVEN;

public class Parser {
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yy");
    private static final int EXERCISE_NAME_INDEX = 0;
    private static final int WEIGHT_INDEX = 1;
    private static final int REPS_PER_SET_INDEX = 2;
    private static final int DATE_INDEX = 0;
    private static final int FOOD_INDEX = 1;
    private static final int CALORIES_INDEX = 2;
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
            return new ListWorkoutCommand();
        case "/view":
            return processView(arguments);
        case "/end":
            return new EndWorkoutCommand();
        case "/exit":
            return new ExitCommand();
        case "/help":
            return new HelpWorkoutCommand();
        case "/cadd":
            return processAddCalories(arguments);
        default:
            return new IncorrectCommand();
        }
    }

    private Command processAddCalories(String arguments) {
        Date date;
        String food;
        int calories;

        try {
            String[] addCaloriesArguments = arguments.trim().split("\\s+", 3);
            date = DATE_FORMAT.parse(addCaloriesArguments[DATE_INDEX].trim());
            food = addCaloriesArguments[FOOD_INDEX].trim();

            if (addCaloriesArguments.length == 3) {
                calories = Integer.parseInt(addCaloriesArguments[CALORIES_INDEX].trim());
            } else {
                calories = CALORIES_NOT_GIVEN;
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format dd/mm/yy.");
            return new IncorrectCommand();
        } catch (NumberFormatException e) {
            System.out.println("Invalid calories format. Please enter an integer");
            return new IncorrectCommand();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid command format.");
            return new IncorrectCommand();
        }

        return new AddCalorieCommand(date, food, calories);
    }

    private Command processStart(String arguments) {
        Date date;
        try {
            date = DATE_FORMAT.parse(arguments);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format dd/mm/yy.");
            return new IncorrectCommand();
        }
        return new StartWorkoutCommand(date);
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
        return new AddWorkoutCommand(toAdd);
    }

    private Command processDelete(String arguments) {
        Date date;
        try {
            date = DATE_FORMAT.parse(arguments);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format dd/mm/yy.");
            return new IncorrectCommand();
        }

        return new DeleteWorkoutCommand(date);
    }

    private Command processView(String arguments) {
        Date date;
        try {
            date = DATE_FORMAT.parse(arguments);
            System.out.println("Here are the list of exercises on" + date + ":");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format dd/mm/yy.");
            return new IncorrectCommand();
        }
        return new ViewWorkoutCommand(date);
    }
}


