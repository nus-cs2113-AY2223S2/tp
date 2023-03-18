package seedu.duke.states;

import java.time.LocalDateTime;

import seedu.duke.exceptions.DukeError;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.storage.UserCareerData;
import seedu.duke.ui.Ui;
import seedu.duke.storage.WriteUserData;
import seedu.duke.userdata.Session;

import java.util.ArrayList;

public class ExerciseStateHandler {
    private static final String SESSION_HISTORY_FILENAME = "userData.json";

    private static WriteUserData dataWriter = new WriteUserData();
    private Session currentSessionWorkout = new Session(null);
    private static ArrayList<ExerciseData> previousGeneratedWorkout = new ArrayList<>();
    public boolean workoutOngoing;

    public void storePreviousGeneratedWorkout(ArrayList<ExerciseData> previousWorkout){
        previousGeneratedWorkout = previousWorkout;
    }

    public void startWorkout(){
        System.out.println("Start workout");
        currentSessionWorkout = new Session(previousGeneratedWorkout);
        workoutOngoing = true;
    }

    public void printCurrentWorkout() throws DukeError {
        if (workoutOngoing == false){
            throw new DukeError("There is no current workout session!");
        }
        Ui ui = new Ui();
        ui.printExerciseFromList(currentSessionWorkout.getSessionExercises());
    }


    public void endWorkout(boolean workoutCompleted, UserCareerData userCareerData){
        workoutOngoing = false;
        if (workoutCompleted) {
            saveWorkoutSession(currentSessionWorkout, userCareerData);
        }
        currentSessionWorkout = null;

    }

    private static void saveWorkoutSession(Session completedWorkout, UserCareerData userCareerData){
        System.out.println("Workout completed");
        userCareerData.addWorkoutSession(completedWorkout);
        WriteUserData writeUserData = new WriteUserData();
        writeUserData.writeToJson(SESSION_HISTORY_FILENAME, userCareerData);
        //complete workout
    }
}
