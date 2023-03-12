package seedu.duke;

import java.util.ArrayList;

public class Workout {
    private String date;
    private ArrayList<Exercise> exerciseList;

    public Workout(String date) {
        this.date = date;
        exerciseList = new ArrayList<>();
    }

    public void addExercise(Exercise exercise) {
        exerciseList.add(exercise);
    }

    public String getDate() {
        return date;
    }
}
