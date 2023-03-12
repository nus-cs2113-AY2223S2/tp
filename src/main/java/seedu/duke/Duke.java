package seedu.duke;

import java.util.Currency;
import java.util.Scanner;

public class Duke {


    public static final String WELCOME_MESSAGE = "Let's get moving!\n" + "\"/start <DD/MM/YY>\" to begin " +
            "your workout";
    private static final int COMMAND_INDEX = 0;
    private static final int NAME_INDEX = 0;
    private static final int DATE_INDEX = 1;
    private static final int EXERCISE_DETAILS_INDEX = 1;
    private static final int WEIGHT_INDEX = 1;
    private static final int REPS_PER_SET_INDEX = 2;

    public static Workout currentWorkout;
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        System.out.println(WELCOME_MESSAGE);
        Scanner in = new Scanner(System.in);
        Boolean isExit = false;

        while (!isExit) {
            String[] userInputs = processUserInput(in.nextLine());
            switch(userInputs[COMMAND_INDEX]) {
            case "/start":
                startWorkout(userInputs);
                break;
            case "/add":
                addExerciseToWorkout(userInputs);
                break;
            case "/end":
                endWorkout();
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

    private static void endWorkout() {
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
            System.out.println("Started new workout on " + currentWorkout.getDate());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect workout format!");
        }
    }
}
