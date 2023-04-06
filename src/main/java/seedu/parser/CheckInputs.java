package seedu.parser;

import seedu.commands.Command;
import seedu.commands.HandlingStringInput;
import seedu.commands.IncorrectSyntaxCommand;
import seedu.commands.countcommands.CountSetsRepsCommand;
import seedu.commands.workoutcommands.AddWorkoutCommand;
import seedu.commands.workoutcommands.DeleteWorkoutCommand;
import seedu.commands.workoutcommands.ListWorkoutCommand;
import seedu.commands.workoutcommands.StartWorkoutCommand;
import seedu.commands.workoutcommands.ViewWorkoutCommand;
import seedu.commands.workoutcommands.StartDayCommand;
import seedu.workout.Exercise;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class CheckInputs {
    private static final int FIRST_ELEMENT = 0;
    private static final int SECOND_ELEMENT = 1;
    private static final int THIRD_ELEMENT = 2;

    private static final String INVALID_START_COMMAND = "Please follow the format /wstart dd/MM/yy.";
    private static final String INVALID_DATE = "Please enter a valid date.";
    private static final String INVALID_DATE_FORMAT = "Invalid date format. Please follow the format dd/MM/yy.";
    private static final String INVALID_YEAR = "Invalid year. Please use a 2 or 4 digit year between 0 and ";
    private static final String DAY_COMMAND = "/wday command";
    private static final String START_COMMAND = "/wstart command";
    private static final String ADD_COMMAND = "/wadd command";
    private static final String LIST_COMMAND = "/wlist command";
    private static final String VIEW_COMMAND = "/wview command";
    private static final String DELETE_COMMAND = "/wdelete command";

    static Command processDay(String arguments) {
        Date date = parseDate(arguments);
        if (date != null && parseInput(arguments)) {
            Command.setIsDayEntered(true);
            Command.setIsWorkoutEntered(false);
            return new StartDayCommand(date);
        }
        return new IncorrectSyntaxCommand(DAY_COMMAND);
    }

    static Command processAdd(String arguments) {
        Exercise toAdd;
        try {
            String[] exerciseDetails = arguments.trim().split("/");
            if (exerciseDetails.length != 3) {
                return new IncorrectSyntaxCommand(ADD_COMMAND);
            }

            String exerciseName = exerciseDetails[FIRST_ELEMENT].trim();
            if (HandlingStringInput.isInputValid(exerciseName.trim())) { //if either condition failed
                return new IncorrectSyntaxCommand(ADD_COMMAND);
            }
            String weight = exerciseDetails[SECOND_ELEMENT].replace("weight", " ").trim();
            String[] checkWeight = exerciseDetails[SECOND_ELEMENT].trim().split(" ");
            if (!exerciseDetails[SECOND_ELEMENT].trim().startsWith("weight ")) {
                return new IncorrectSyntaxCommand(ADD_COMMAND);
            }
            if (!HandlingStringInput.isInputTooLong(checkWeight[SECOND_ELEMENT])
                    || !HandlingStringInput.isInputMatching(checkWeight[SECOND_ELEMENT].trim())) {
                return new IncorrectSyntaxCommand(ADD_COMMAND);
            }


            String[] rpsList = exerciseDetails[THIRD_ELEMENT].trim().split(" ", 2);


            if (!exerciseDetails[THIRD_ELEMENT].startsWith("rps ")) {
                return new IncorrectSyntaxCommand(ADD_COMMAND);
            }

            String[] rpsStringList = rpsList[SECOND_ELEMENT].trim().split(",", 10);
            if (rpsStringList.length > 10) {
                System.out.println("The number of sets for rps up to 10");
            }
            String[] newRps = new String[rpsStringList.length];
            int[] reps = new int[rpsStringList.length];
            for (int index = 0; index < rpsStringList.length; index += 1) {
                newRps[index] = rpsStringList[index].trim();
                reps[index] = Integer.parseInt(rpsStringList[index].trim());
            }

            toAdd = new Exercise(exerciseName, weight, Arrays.toString(newRps).replaceAll("[\\[\\],]", ""));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            return new IncorrectSyntaxCommand(ADD_COMMAND);
        }
        return new AddWorkoutCommand(toAdd);
    }

    /**
     * This method is used to check the "/start" command
     * If the date input has incorrect format, it will notify the users
     * Otherwise, StartCommand will be executed
     *
     * @param arguments Date input
     * @return Incorrect command if the input date is incorrect, otherwise, initialize the StartCommand
     */
    static Command processStart(String arguments) {
        //
        String workName = arguments.replaceAll(" {2,}", " ");
        if (parseWorkoutName(workName)) {
            Command.setIsWorkoutEntered(true);
            return new StartWorkoutCommand(workName.trim());
        } else {
            return new IncorrectSyntaxCommand(START_COMMAND);
        }

    }

    /**
     * This method is used to check the "/delete" command
     *
     * @param arguments Date input
     * @return Incorrect command if the input date is incorrect, otherwise, initialize the StartCommand
     */
    static Command processDelete(String arguments) {
        Date date = parseDate(arguments);
        return date != null && parseInput(arguments) ? new DeleteWorkoutCommand(date) :
                new IncorrectSyntaxCommand(DELETE_COMMAND);
    }

    //@@ author ZIZI-czh
    static Command processList(String arguments) {
        return arguments == null || arguments.trim().isEmpty() ? new ListWorkoutCommand() :
                new IncorrectSyntaxCommand(LIST_COMMAND);
    }

    /**
     * This method is used to check the "/view" command
     *
     * @param arguments Date input
     * @return Incorrect command if the input date is incorrect, otherwise, initialize the ViewCommand
     */
    //@@ author Richardtok
    static Command processView(String arguments) {
        Date date;
        try {
            date = DateFormatter.stringToDate(arguments);
            return new ViewWorkoutCommand(date);
        } catch (ParseException e) {
            return new IncorrectSyntaxCommand(VIEW_COMMAND);
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
     *
     * @param arguments inputs date
     * @return return null if the date format is invalid
     */
    //@@ author ZIZI-czh
    static Date parseDate(String arguments) {
        try {
            Date enteredDate = DateFormatter.stringToDate(arguments);
            ;

            return enteredDate;
        } catch (ParseException e) {
            System.out.println(INVALID_DATE_FORMAT);
            return null;
        }
    }

    /**
     * This method is used to check the input command based on the format and the date
     *
     * @param arguments input
     * @return return false if the input does not follow the format
     */

    //@@ author ZIZI-czh
    static boolean parseInput(String arguments) {
        if (arguments.trim().contains("\\s+")) {
            System.out.println(INVALID_START_COMMAND);
            return false; // not in the form "/start dd/MM/yy"
        }
        String[] dateParts = arguments.trim().split("/");

        if (dateParts.length != 3) {
            System.out.println(INVALID_START_COMMAND);
            return false; // not in the form "dd/MM/yy"
        }

        for (String part : dateParts) {
            if (part.contains("/")) {
                System.out.println(INVALID_START_COMMAND);
                return false; // non-numeric character found in date
            }
        }

        int day;
        int month;
        int year;
        try {
            day = Integer.parseInt(dateParts[0].trim());
            month = Integer.parseInt(dateParts[1].trim());
            year = Integer.parseInt(dateParts[2].trim());
            if (day < 1 || day > 31 || month < 1 || month > 12) {
                System.out.println(INVALID_DATE);
                return false; // not valid date components
            }
            if (year < 0 || (year > 23 && year < 1000) || year > Calendar.getInstance().get(Calendar.YEAR)) {
                System.out.println(INVALID_YEAR
                        + Calendar.getInstance().get(Calendar.YEAR));
            }
        } catch (NumberFormatException e) {
            System.out.println(INVALID_DATE);
            return false; // not valid integers for date components
        }

        return true; // input is in the correct format
    }

    public static boolean parseWorkoutName(String workName) {
        if (HandlingStringInput.isInputValid(workName)) {
            return false;
        }
        return !workName.trim().isEmpty();
    }


}
