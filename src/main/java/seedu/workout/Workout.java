package seedu.workout;

import java.util.ArrayList;
import java.util.Date;

public class Workout {
    private Date date;
    ArrayList<Exercise> workoutExercises;

    public Workout(Date date) {
        this.date = date;
        workoutExercises = new ArrayList<>();
    }

    public void addExercise(Exercise exercise) {
        workoutExercises.add(exercise);
        System.out.println("Added " + exercise);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
