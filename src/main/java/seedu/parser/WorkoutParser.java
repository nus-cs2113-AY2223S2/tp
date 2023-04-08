package seedu.parser;

import seedu.commands.Command;
import seedu.commands.workoutcommands.AddExerciseCommand;
import seedu.commands.workoutcommands.CountSetsRepsCommand;
import seedu.commands.workoutcommands.DeleteWorkoutCommand;
import seedu.commands.workoutcommands.EndWorkoutCommand;
import seedu.commands.workoutcommands.ListWorkoutCommand;
import seedu.commands.workoutcommands.StartWorkoutCommand;
import seedu.commands.workoutcommands.ViewWorkoutCommand;
import seedu.exceptions.InvalidArgumentException;
import seedu.exceptions.InvalidSyntaxException;
import seedu.workout.Exercise;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.max;
import static seedu.parser.Parser.parseDate;

/**
 * Represents the parser for workout commands.
 */
public class WorkoutParser {
    private static final int EXERCISE_NAME_INDEX = 0;
    private static final int WEIGHT_INDEX = 1;
    private static final int REPS_PER_SET_INDEX = 2;
    private static final int ADD_ARGUMENT_COUNT = 3;
    private static final int START_WORKOUT_ARGUMENT_COUNT = 2;
    private static final int DATE_INDEX = 0;
    private static final int WORKOUT_NAME_INDEX = 1;

    //@@author calebcjl
    /**
     * Parse arguments for /wadd command.
     *
     * @param arguments Arguments for add workout command.
     * @return AddWorkoutCommand if arguments are valid.
     * @throws InvalidSyntaxException If there is invalid syntax.
     */
    static Command parseAddExerciseCommand(String arguments)
            throws InvalidSyntaxException, InvalidArgumentException {
        //exercise name 100kg 5 5 5 5
        String[] exerciseDetails = new String[ADD_ARGUMENT_COUNT];
        Matcher matcher = Pattern.compile("\\d+").matcher(arguments);
        matcher.find();
        try {
            exerciseDetails[EXERCISE_NAME_INDEX] = arguments.substring(0, arguments.indexOf(matcher.group()));
            exerciseDetails[WEIGHT_INDEX] = arguments.substring(arguments.indexOf(matcher.group()),
                    max(arguments.indexOf("kg"), arguments.indexOf("lb")) + 2);
            exerciseDetails[REPS_PER_SET_INDEX] = arguments.substring
                    (arguments.indexOf(exerciseDetails[WEIGHT_INDEX]) + exerciseDetails[WEIGHT_INDEX].length());
        } catch (IndexOutOfBoundsException | IllegalStateException e) {
            throw new InvalidSyntaxException("/wadd command");
        }

        String exerciseName = parseExerciseName(exerciseDetails[EXERCISE_NAME_INDEX]);
        String weight = parseWeight(exerciseDetails[WEIGHT_INDEX]);
        String repsPerSet = parseRepsPerSet(exerciseDetails[REPS_PER_SET_INDEX]);
        Exercise toAdd = new Exercise(exerciseName, weight, repsPerSet);

        return new AddExerciseCommand(toAdd);
    }

    //@@author calebcjl
    /**
     * Check if name is valid.
     * Name is valid if it is not an empty string.
     *
     * @param name Name to be checked.
     * @return True if valid. Returns false otherwise.
     */
    private static boolean isValidName(String name) {
        return name != null && !name.isEmpty();
    }

    //@@author calebcjl
    /**
     * Parses exercise name argument.
     * Removes any leading and trailing whitespaces.
     *
     * @param exerciseName Exercise name argument.
     * @return Exercise name.
     * @throws InvalidSyntaxException If syntax is invalid.
     */
    private static String parseExerciseName(String exerciseName) throws InvalidArgumentException {
        exerciseName = exerciseName.trim();
        if (!isValidName(exerciseName)) {
            throw new InvalidArgumentException("exercise name");
        }
        return exerciseName;
    }

    //@@author calebcjl
    /**
     * Checks if weight is valid.
     * A valid weight contains a positive number with at most 2 decimal place and weight unit (kg or lb).
     *
     * @param weight Weight to be checked.
     * @return True if weight is valid. Returns false otherwise.
     */
    private static boolean isValidWeight(String weight) {
        if (weight == null || weight.isEmpty()) {
            return false;
        }
        if (!weight.contains("kg") && !weight.contains("lb")) {
            return false;
        }
        try {
            Integer.parseUnsignedInt(weight.substring(0, max(weight.indexOf("kg"), weight.indexOf("lb"))));
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Parses weight argument.
     *
     * @param weight Weight argument.
     * @return Weight.
     */
    private static String parseWeight(String weight) throws InvalidArgumentException {
        weight = weight.trim();
        if (!isValidWeight(weight)) {
            throw new InvalidArgumentException("weight");
        }
        return weight;
    }

    //@@author calebcjl
    /**
     * Check if reps per set are valid.
     * A valid reps per set is a String of positive integers separated by a single whitespace.
     *
     * @param repsPerSet Reps per set argument.
     * @return True if reps per set are valid. Returns false otherwise.
     */
    private static boolean isValidRepsPerSet(String repsPerSet) {
        String[] reps = repsPerSet.split(" ");
        try {
            for (String repCount : reps) {
                Integer.parseUnsignedInt(repCount);
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    //@@author calebcjl
    /**
     * Parses reps per set argument.
     *
     * @param repsPerSet Reps per set argument.
     * @return Reps per set.
     */
    private static String parseRepsPerSet(String repsPerSet) throws InvalidArgumentException {
        repsPerSet = repsPerSet.trim();
        if (!isValidRepsPerSet(repsPerSet)) {
            throw new InvalidArgumentException("reps per set");
        }
        return repsPerSet;
    }

    //@@author ZIZI-czh
    /**
     * This method is used to check the "/start" command
     * If the date input has incorrect format, it will notify the users
     * Otherwise, StartCommand will be executed
     *
     * @param arguments Date input
     * @return Incorrect command if the input date is incorrect, otherwise, initialize the StartCommand
     */
    static Command parseStartWorkoutCommand(String arguments) throws InvalidSyntaxException,
            InvalidArgumentException {
        String[] startDetails = arguments.trim().split("\\s+", START_WORKOUT_ARGUMENT_COUNT);
        if (startDetails.length != START_WORKOUT_ARGUMENT_COUNT) {
            throw new InvalidSyntaxException("/wstart command");
        }
        Date date = parseDate(startDetails[DATE_INDEX]);
        String workoutName = parseWorkoutName(startDetails[WORKOUT_NAME_INDEX]);

        return new StartWorkoutCommand(date, workoutName);
    }

    static String parseWorkoutName(String workoutName) throws InvalidArgumentException {
        if (workoutName.isBlank()) {
            throw new InvalidArgumentException("workout name");
        }
        return workoutName.trim();
    }

    /**
     * This method is used to check the "/delete" command
     *
     * @param arguments Date input
     * @return Incorrect command if the input date is incorrect, otherwise, initialize the StartCommand
     */
    static Command parseDeleteWorkoutCommand(String arguments) throws InvalidSyntaxException {
        arguments = arguments.trim();
        int index;
        try {
            index = Integer.parseUnsignedInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidSyntaxException("/wdelete command");
        }
        return new DeleteWorkoutCommand(index);
    }

    //@@ author ZIZI-czh
    static Command parseListWorkoutCommand(String arguments) throws InvalidSyntaxException {
        if (arguments != null && !arguments.isBlank()) {
            throw new InvalidSyntaxException("/wlist command");
        }
        return new ListWorkoutCommand();
    }


    //@@ author Richardtok
    static Command parseViewWorkoutCommand(String arguments) throws InvalidArgumentException {
        arguments = arguments.trim();
        int index;
        try {
            index = Integer.parseUnsignedInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("index");
        }
        return new ViewWorkoutCommand(index);
    }

    //@@ author guillaume-grn
    static Command parseSetsRepsCountCommand(String arguments) throws InvalidArgumentException,
            InvalidSyntaxException {
        Date date = parseDate(arguments);
        return new CountSetsRepsCommand(date);
    }




    //@@author calebcjl
    /**
     * Parses arguments of end workout command.
     *
     * @param arguments Arguments of end workout command.
     * @return End workout command.
     * @throws InvalidSyntaxException If syntax of command is invalid.
     */
    static Command parseEndWorkoutCommand(String arguments) throws InvalidSyntaxException {
        if (arguments != null && !arguments.isBlank()) {
            throw new InvalidSyntaxException("/wend command");
        }
        return new EndWorkoutCommand();
    }
}
