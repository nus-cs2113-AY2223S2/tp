package seedu.workout;

import java.util.ArrayList;

public class Workout {

    private String workoutName;
    private ArrayList<Exercise> exercises;


    public Workout() {
        exercises = new ArrayList<>();
    }

    //@@ author ZIZI-czh
    public Workout(String workoutName) {
        this.workoutName = workoutName;
        exercises = new ArrayList<>();
    }


    //@@ author ZIZI-czh
    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
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

