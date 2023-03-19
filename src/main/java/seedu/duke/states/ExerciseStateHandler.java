package seedu.duke.states;

import seedu.duke.exceptions.DukeError;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.storage.StorageHandler;
import seedu.duke.userdata.UserCareerData;
import seedu.duke.ui.Ui;
import seedu.duke.userdata.Session;

import java.util.ArrayList;

public class ExerciseStateHandler {

    private static ArrayList<ExerciseData> previousGeneratedWorkout = new ArrayList<>();
    private final StorageHandler storageHandler;
    public boolean workoutOngoing;
    private Session currentSessionWorkout;

    public ExerciseStateHandler (StorageHandler storageHandler) {
        this.storageHandler = storageHandler;
        this.currentSessionWorkout = new Session(null);
    }

    /**
     * This function logs the previous workout everytime a workout is generated
     * (In other words, whenever the generate command is called)
     *
     * @param previousWorkout Temporarily logs the most recent generated exercise list
     */
    public void storePreviousGeneratedWorkout (ArrayList<ExerciseData> previousWorkout) {
        assert previousWorkout != null;
        previousGeneratedWorkout = previousWorkout;
    }

    /**
     * This function switches the state of how Command Handler functions,
     * blocking off certain commands until the session has ended
     */
    public void startWorkout () {
        System.out.println("Start workout! You got this, all the best!");
        currentSessionWorkout = new Session(previousGeneratedWorkout);
        workoutOngoing = true;
    }

    /**
     * Prints the current workout if it exists
     * Otherwise throws an error
     *
     * @throws DukeError Throws an error if there is no ongoing exercise session
     */
    public void printCurrentWorkout () throws DukeError {
        if (!workoutOngoing) {
            throw new DukeError("There is no current workout session!" +
                                        "Please start a session now");
        }
        Ui ui = new Ui();
        ui.printExerciseFromList(currentSessionWorkout.getSessionExercises());
    }

    /**
     * This ends the current workout, resuming access to other functions
     *
     * @param workoutCompleted Will add current session to saved sessions if true
     * @param userCareerData Stores and contains user data
     */
    public void endWorkout (boolean workoutCompleted, UserCareerData userCareerData) throws DukeError {
        assert userCareerData != null;
        workoutOngoing = false;
        if (workoutCompleted) {
            saveWorkoutSession(currentSessionWorkout, userCareerData);
        }
        currentSessionWorkout = null;
    }

    /**
     * Prints congratulation message and saves the completed session
     *
     * @param completedWorkout The workout to be saved to userData.json
     * @param userCareerData Stores User Data
     */
    private void saveWorkoutSession (Session completedWorkout, UserCareerData userCareerData) throws DukeError {
        assert completedWorkout != null;
        System.out.println("Workout completed! Congratulations on your hard work!");
        userCareerData.addWorkoutSession(completedWorkout);
        storageHandler.writeToJson(userCareerData);
        //complete workout
    }

}
