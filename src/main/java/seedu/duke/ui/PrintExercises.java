package seedu.duke.ui;

import seedu.duke.exersisedata.ExerciseData;

import java.util.ArrayList;

public class PrintExercises {
    public static void printExercise(ArrayList<ExerciseData> exercises) {
        for (ExerciseData exercise : exercises) {
            System.out.println(exercise.getName());
            System.out.println("Difficulty Level: " + exercise.getLevel());
            System.out.println(exercise.getInstructions() + "\n");
        }
    }
}
