package seedu.workout;


import seedu.parser.DateFormatter;

import java.util.ArrayList;
import java.util.Date;

public class Workout {
    private static final int EMPTY = 0;
    private static final String EMPTY_EXERCISE_LIST_MESSAGE = "No exercise in workout.";
    private static final String EXERCISE_LIST_HEADER =
            "Here are the list of exercises in your workout:" + System.lineSeparator();
    private Date date;
    private ArrayList<Exercise> workoutExercises;

    public Workout(Date date) {
        this.date = date;
        workoutExercises = new ArrayList<>();
    }

    public void addExercise(Exercise exercise) {
        workoutExercises.add(exercise);
    }

    public String getDateToString() {
        return DateFormatter.dateToString(date);
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

    public String toString() {
        if (workoutExercises.size() == EMPTY) {
            return EMPTY_EXERCISE_LIST_MESSAGE;
        }

        StringBuilder exerciseListString = new StringBuilder();
        exerciseListString.append(EXERCISE_LIST_HEADER);

        for (int i = 0; i < workoutExercises.size(); i += 1) {
            exerciseListString.append(i + 1).append("." + workoutExercises.get(i).toString() + System.lineSeparator());
        }

        return exerciseListString.toString();
    }
}

