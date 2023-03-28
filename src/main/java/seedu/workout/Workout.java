package seedu.workout;


import seedu.DateFormat;

import java.util.ArrayList;
import java.util.Date;

public class Workout {
    private Date date;
    private ArrayList<Exercise> workoutExercises;

    public Workout(Date date) {
        this.date = date;
        workoutExercises = new ArrayList<>();
    }

    public void addExercise(Exercise exercise) {
        workoutExercises.add(exercise);
        System.out.println("Added " + exercise);
    }

    public String getStringDate() {
        DateFormat dateFormat = new DateFormat(date);
        return dateFormat.formatDate();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public ArrayList<Exercise> getExercises() {
        return workoutExercises;
    }
}

