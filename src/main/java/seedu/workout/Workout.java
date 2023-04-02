package seedu.workout;

import java.util.ArrayList;
import java.util.Date;

public class Workout {
    private static final int EMPTY = 0;
    private static final String EMPTY_EXERCISE_LIST_MESSAGE = "No exercise in workout.";
    private static final String LINE_SEPARATOR = "----------------------------------";
    private static final String EXERCISE_LIST_HEADER =
            "Here are the list of exercises in your workout:" + System.lineSeparator();
    private Date date;
    private String workoutName;
    private ArrayList<Exercise> exercises;

    private boolean hasExercises = false;

    public Workout() {
        exercises = new ArrayList<>();
    }

    //@@ author ZIZI-czh
    public Workout(String workoutName) {
        this.workoutName = workoutName;
        exercises = new ArrayList<>();
    }


    //@@ author ZIZI-czh
    public String getWorkoutName() {
        return workoutName;
    }

    //@@ author ZIZI-czh

    //@@ author ZIZI-czh
    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
        // hasExercises = true;
    }

    //@@ author ZIZI-czh
    public ArrayList<Exercise> getExercises() {
        return exercises;
    }


    //@@ author ZIZI-czh
    @Override
    public String toString() {
        StringBuilder exercisesList = new StringBuilder();
        exercisesList.append("Workout: ").append(workoutName).append(System.lineSeparator());
        for (Exercise exercise : exercises) {
            exercisesList.append(exercise.toString()).append(System.lineSeparator());
        }
        return exercisesList.toString();
    }
}

