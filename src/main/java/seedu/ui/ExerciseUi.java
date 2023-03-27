package seedu.ui;

import seedu.entities.Exercise;

public class ExerciseUi extends GeneralUi {
    public static void addedExercise(Exercise exercise) {
        System.out.println("Added this exercise");
        System.out.println(exercise);
    }
}
