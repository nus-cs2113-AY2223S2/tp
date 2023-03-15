package seedu.duke;

import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.DateFormat;

import seedu.workout.Exercise;
import seedu.workout.Workout;
import seedu.workout.WorkoutList;

public class Duke {

    public static final String WELCOME_MESSAGE = "Let's get moving!\n" + "\"/start <DD/MM/YY>\" to begin " +
            "your workout";
    private static final int COMMAND_INDEX = 0;
    private static final int NAME_INDEX = 0;
    private static final int DATE_INDEX = 1;
    private static final int EXERCISE_DETAILS_INDEX = 1;
    private static final int WEIGHT_INDEX = 1;
    private static final int REPS_PER_SET_INDEX = 2;

    private static Workout currentWorkout;
    private static WorkoutList workoutList;

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        System.out.println(WELCOME_MESSAGE);
        Scanner in = new Scanner(System.in);
        workoutList = new WorkoutList();
        Boolean isExit = false;

        while (!isExit) {
            String[] userInputs = processUserInput(in.nextLine());
            switch (userInputs[COMMAND_INDEX]) {
            case "/start":
                startWorkout(userInputs);
                break;
            case "/add":
                addExerciseToWorkout(userInputs);
                break;
            case "/delete":
                deleteWorkout(userInputs);
                break;
            case "/end":
                endWorkout();
                break;
            case "/exit":
                isExit = true;
                System.out.println("Hope you had a great workout!");
                break;
            default:
                System.out.println("Command not recognised!");
                break;
            }
        }
    }

    private static void deleteWorkout(String[] userInputs) {
        String dateString = userInputs[DATE_INDEX];
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            Date date = dateFormat.parse(dateString);

            // Search for the workout with the matching date and remove it
            workoutList.removeWorkout(date);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format dd/mm/yy.");
        }
    }

    private static void endWorkout() {
        workoutList.addWorkout(currentWorkout);
        currentWorkout = null;
        System.out.println("Ended current workout!");
    }

    private static void addExerciseToWorkout(String[] inputs) {
        try {
            String[] exerciseDetails = processExerciseDetails(inputs[EXERCISE_DETAILS_INDEX]);
            Exercise exercise = new Exercise(exerciseDetails[NAME_INDEX], exerciseDetails[WEIGHT_INDEX],
                    exerciseDetails[REPS_PER_SET_INDEX]);
            currentWorkout.addExercise(exercise);
            System.out.println("Added " + exercise);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect exercise format!");
        } catch (NullPointerException e) {
            System.out.println("Start a workout first!");
        }
    }

    public static String[] processUserInput(String input) {
        String[] inputs = input.split("\\s+", 2);
        return inputs;
    }

    public static String[] processExerciseDetails(String userInput) {
        String[] exerciseDetails = userInput.split("/", 3);
        exerciseDetails[NAME_INDEX] = exerciseDetails[NAME_INDEX].trim();
        exerciseDetails[WEIGHT_INDEX] = exerciseDetails[WEIGHT_INDEX].replace("weight", "").trim();
        exerciseDetails[REPS_PER_SET_INDEX] = exerciseDetails[REPS_PER_SET_INDEX].replace("rps", "").trim();
        return exerciseDetails;
    }

    public static void startWorkout(String[] inputs) {
        try {
            currentWorkout = new Workout(inputs[DATE_INDEX]);
            System.out.println("Started new workout.");
            System.out.println("\"/add <exercise_name> /weight <weight_used> /rps <reps_per_set>\"" + "to " +
                    "add your exercises sets and reps");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect workout format!");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format dd/mm/yy.");
        }
    }
}
