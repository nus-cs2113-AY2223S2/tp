package seedu.workout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Workout {
    private String date;
    private ArrayList<Exercise> workoutExercises;

    public Workout(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        this.date = dateFormat.format(date);
        workoutExercises = new ArrayList<>();
    }

    public void addExercise(Exercise exercise) {
        workoutExercises.add(exercise);
        System.out.println("Added " + exercise);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
