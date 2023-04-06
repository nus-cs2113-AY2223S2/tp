package seedu.duke.data.userdata;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;

import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;

public class Session {
    private final LocalDateTime dateAdded;
    private final ArrayList<ExerciseData> sessionExercises;
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

    protected void checkExerciseDataNullity () throws DukeError {
        for (ExerciseData exerciseData : sessionExercises) {
            try {
                if (!exerciseData.checkExerciseNullity()) {
                    throw new DukeError("Null in exerciseData");
                }
            } catch (Exception e) {
                throw new DukeError("Null in exerciseData");
            }
        }
    }

    public boolean checkSessionNullity () throws DukeError {
        for (Field f : getClass().getDeclaredFields()) {
            try {
                checkExerciseDataNullity();
                if (f.get(this) == null) {
                    return false;
                }
            } catch (Exception e) {
                throw new DukeError("Null element in Session");
            }
        }
        return true;
    }

}
