package seedu.workout;

import seedu.parser.DateFormatter;
import seedu.ui.Ui;

import java.util.ArrayList;
import java.util.Date;

//@@author calebcjl
/**
 * Represents a workout. It stores the date, name and list of exercises of the workout.
 */
public class Workout {
    private Date date;
    private String workoutName;
    private final ArrayList<Exercise> exercises;

    public Workout() {
        exercises = new ArrayList<>();
    }

    public Workout(Date date, String workoutName) {
        this.date = date;
        this.workoutName = workoutName;
        exercises = new ArrayList<>();
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    /**
     * Returns the list of exercises in the workout.
     *
     * @return List of exercises.
     */
    @Override
    public String toString() {
        String header = "Here are the list of exercises for " + getWorkoutName() + " on "
                + DateFormatter.dateToString(date) + '.' + System.lineSeparator();
        StringBuilder exercisesList = new StringBuilder(header);
        int counter = 1;
        for (Exercise exercise : exercises) {
            exercisesList.append(counter).append(". ").append(exercise.toString()).append(System.lineSeparator());
            counter += 1;
        }
        return exercisesList.append(Ui.line()).toString();
    }
}

