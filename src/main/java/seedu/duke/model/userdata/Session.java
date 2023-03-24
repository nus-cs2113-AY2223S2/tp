package seedu.duke.model.userdata;

import java.time.LocalDateTime;
import java.util.ArrayList;

import seedu.duke.model.exercisegenerator.exersisedata.ExerciseData;

public class Session {
    private final LocalDateTime dateAdded;
    private final ArrayList<ExerciseData> sessionExercises;
    private LocalDateTime dateComplete;
    private String status;

    /**
     * Constructs a new workout that the user has added to their list.
     * Initialises the status of the workout and sets the datetime when
     * workout was added
     *
     * @param sessionExercises the exercise which the user plans to do
     */
    public Session (ArrayList<ExerciseData> sessionExercises) {
        this.sessionExercises = sessionExercises;
        this.dateAdded = LocalDateTime.now();
        this.status = "Incomplete";
    }

    /**
     * Gets the current status of the Workout Session to check its completion status
     *
     * @return status of current workout whether it is complete or incomplete
     */
    public String getStatus () {
        return this.status;
    }

    /**
     * Gets the datetime in which the session was last added
     *
     * @return datetime object of when session was added
     */
    public LocalDateTime getDateAdded () {
        return this.dateAdded;
    }

    /**
     * Gets the ArrayList of all exercises listed in the current session and returns to the user
     *
     * @return exercise data of the exercise added to their session
     */
    public ArrayList<ExerciseData> getSessionExercises () {
        return this.sessionExercises;
    }

    /**
     * Marks the session as completed
     */
    public void markComplete () {
        this.status = "Complete";
        this.dateComplete = LocalDateTime.now();
    }

}
