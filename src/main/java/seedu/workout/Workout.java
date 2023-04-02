package seedu.workout;

import seedu.parser.DateFormatter;
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
    private  ArrayList<Exercise> exercises;

    private boolean hasExercises = false;



    public boolean isHasExercises() {
        return hasExercises;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public Workout(String workoutName) {
        this.workoutName = workoutName;
        exercises = new ArrayList<>();
    }
    public Workout() {

        exercises = new ArrayList<>();
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
       // hasExercises = true;
    }
    public ArrayList<Exercise> getExercises() {
        return exercises;
    }



    @Override
    public  String toString() {
        StringBuilder exercisesList = new StringBuilder();
        exercisesList.append("Workout: ").append(workoutName).append("\n");
        for (Exercise exercise : exercises) {
            exercisesList.append(exercise.toString()).append("\n");
        }
        return exercisesList.toString();
    }
}

