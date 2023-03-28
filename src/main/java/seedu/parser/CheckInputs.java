package seedu.parser;


import seedu.commands.Command;
import seedu.commands.IncorrectSyntaxCommand;
import seedu.commands.countcommands.CountSetsRepsCommand;
import seedu.commands.workoutcommands.AddWorkoutCommand;
import seedu.commands.workoutcommands.DeleteWorkoutCommand;
import seedu.commands.workoutcommands.ListWorkoutCommand;
import seedu.commands.workoutcommands.StartWorkoutCommand;
import seedu.commands.workoutcommands.ViewWorkoutCommand;
import seedu.workout.Exercise;

import java.text.ParseException;
import java.util.Date;

public class CheckInputs {
    private static final int EXERCISE_NAME_INDEX = 0;
    private static final int WEIGHT_INDEX = 1;
    private static final int REPS_PER_SET_INDEX = 2;


    static Command processAdd(String arguments) {
        Exercise toAdd;
        try {
            String[] exerciseDetails = arguments.split("/", 3);
            String exerciseName = exerciseDetails[EXERCISE_NAME_INDEX].trim();
            String weight = exerciseDetails[WEIGHT_INDEX].replace("weight", "").trim();
            String repsPerSet =
                    exerciseDetails[REPS_PER_SET_INDEX].replace("rps", "").replace("rps", "").trim();
            toAdd = new Exercise(exerciseName, weight, repsPerSet);

        } catch (ArrayIndexOutOfBoundsException e) {
            return new IncorrectSyntaxCommand("/add command");
        }
        return new AddWorkoutCommand(toAdd);
    }

    /**
     * This method used to check the "/start" command
     * If the date input has incorrect format, it will notify the users
     * Otherwise, StartCommand will be executed
     *
     * @param arguments Date input
     * @return Incorrect command if the input date is incorrect, otherwise, initialize the StartCommand
     */
    static Command processStart(String arguments) {
        Date date = parseDate(arguments);
        return date != null && parseInput(arguments) ? new StartWorkoutCommand(date) :
                new IncorrectSyntaxCommand("/start command");
    }

    /**
     * This method used to check the "/delete" command
     *
     * @param arguments Date input
     * @return Incorrect command if the input date is incorrect, otherwise, initialize the StartCommand
     */
    static Command processDelete(String arguments) {
        Date date = parseDate(arguments);
        return date != null && parseInput(arguments) ? new DeleteWorkoutCommand(date) :
                new IncorrectSyntaxCommand("/delete command");
    }

    //@@ author ZIZI-czh
    static Command processList(String arguments) {
        return arguments == null || arguments.trim().isEmpty() ? new ListWorkoutCommand() :
                new IncorrectSyntaxCommand("/list command");
    }


    static Command processView(String arguments) {
        Date date;
        try {
            date = DateFormatter.stringToDate(arguments);
            return new ViewWorkoutCommand(date);
        } catch (ParseException e) {
            return new IncorrectSyntaxCommand("/view command");
        }
    }

    //@@ author guillaume-grn
    static Command processSetsRepsCount(String arguments) {
        Date date;

        try {
            date = DateFormatter.stringToDate(arguments);
        } catch (ParseException e) {
            return new IncorrectSyntaxCommand("date");
        }
        return new CountSetsRepsCommand(date);
    }


    /**
     * This method is used to check the input date format
     * @param arguments inputs date
     * @return return null if the date format is invalid
     */
    //@@ author ZIZI-czh
    private static Date parseDate(String arguments) {
        try {
            Date enteredDate = DateFormatter.stringToDate(arguments);
            Date currentDate = new Date();

            if (enteredDate.compareTo(currentDate) > 0) {
                System.out.println("Date cannot be after the current date.");
                return null;
            }
            return enteredDate;
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please follow the format dd/MM/yy");
            return null;
        }
    }

    /**
     * This method is used to check the input command based on the format and the date
     * @param arguments input
     * @return return false if the input does not follow the format
     */

    //@@ author ZIZI-czh
    private static boolean parseInput(String arguments) {
        //arguments.trim().contains("\\s+");
        if (arguments.trim().contains("\\s+")) {
            System.out.println("invalid input date, please use the format /start dd/MM/yyyy");
            return false; // not in the form "/start dd/MM/yy"
        }
        String[] dateParts = arguments.trim().split("/");

        if (dateParts.length != 3) {
            System.out.println("invalid input, please use the format dd/MM/yyyy");
            return false; // not in the form "dd/MM/yy"
        }

        for (String part : dateParts) {
            if (part.contains("/")) {
                System.out.println("invalid input, please follow /start dd/MM/yyyy");
                return false; // non-numeric character found in date
            }
        }

        int day;
        int month;
        int year;
        try {
            day = Integer.parseInt(dateParts[0]);
            month = Integer.parseInt(dateParts[1]);
            year = Integer.parseInt(dateParts[2]);
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid date");
            return false; // not valid integers for date components
        }
        if (day < 1 || day > 31 || month < 1 || month > 12 || year < 0) {
            System.out.println("Please enter a correct date");
            return false; // not valid date components
        }
        return true; // input is in the correct format
    }


}
