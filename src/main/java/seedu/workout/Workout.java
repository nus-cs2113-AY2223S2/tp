package seedu.workout;

import seedu.parser.DateFormatter;
import seedu.ui.Ui;

import java.time.LocalDateTime;
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

    //@@author ZIZI-czh
    public Workout() {
        exercises = new ArrayList<>();
    }

    //@@author ZIZI-czh
    /**
     * Creates a new Workout object with the given date and workout name.
     * Initializes the exercises list to an empty ArrayList.
     *
     * @param date the date of the workout
     * @param workoutName the name of the workout
     */
    public Workout(Date date, String workoutName) {
        this.date = date;
        this.workoutName = workoutName;
        exercises = new ArrayList<>();
    }


    //@@author ZIZI-czh
    public String getWorkoutName() {
        return workoutName;
    }

    //@@author ZIZI-czh
    public Date getDate() {
        return date;
    }

    //@@author ZIZI-czh
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

