package seedu.workout;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Workout {
    private Date date;
    ArrayList<Exercise> workoutExercises;

    public void addExercise(Exercise exercise) {
        workoutExercises.add(exercise);
    }

    public Workout(String dateString) throws ParseException {
        // Create a SimpleDateFormat object with the desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        // Parse the input dateString to create a Date object
        this.date = dateFormat.parse(dateString);
        workoutExercises = new ArrayList<>();
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
