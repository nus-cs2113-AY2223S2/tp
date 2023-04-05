package seedu.duke.ui;

import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;

import java.util.ArrayList;

public class PrintExercises {
    private static final String OPEN_BRACE = "[";
    private static final String CLOSE_BRACE = "]";

    public static void printExercise (ArrayList<ExerciseData> exercises) {
        String getWorkoutType;
        String getWorkoutTypeFinal;
        String getInstructions;
        String getInstructionsFinal;
        for (ExerciseData exercise : exercises) {
            // Removed open and close braces for instructions as well as workout type.
            getWorkoutType = exercise.getWorkoutType().toString();
            getInstructions = exercise.getInstructions().toString();
            int start = getWorkoutType.indexOf(OPEN_BRACE);
            int end = getWorkoutType.indexOf(CLOSE_BRACE);
            int startInstructions = getInstructions.indexOf(OPEN_BRACE);
            int endInstructions = getInstructions.indexOf(CLOSE_BRACE);
            getWorkoutTypeFinal = getWorkoutType.substring(start + 1, end);
            getInstructionsFinal = getInstructions.substring(startInstructions + 1, endInstructions);
            System.out.println("Exercise ID: " + exercise.getId() + ". ");
            System.out.println("Name: " + exercise.getName());
            System.out.println("Difficulty Level: " + exercise.getLevel());
            System.out.println("Workout Type: " + getWorkoutTypeFinal);
            System.out.println(getInstructionsFinal.replace(",", "") + System.lineSeparator());
        }
    }

}
