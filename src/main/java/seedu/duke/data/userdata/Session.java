package seedu.duke.data.userdata;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;

import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;

//@@author EangJS

/**
 * A class representing a workout session performed by the user
 */
public class Session {
    private final LocalDateTime dateAdded;
    private final ArrayList<ExerciseData> sessionExercises;

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
     * Checks the arraylist of exercises to ensure each exerciseData does not have a null element during storage
     * initialization
     *
     * @throws DukeError Occurs when a null element is found in the exerciseData
     */
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

    /**
     * Checks elements in this class to ensure that no null elements are being read in from storage
     *
     * @return boolean value indicating true if there are no null elements
     *
     * @throws DukeError Occurs when there is a null element in the object
     */
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
