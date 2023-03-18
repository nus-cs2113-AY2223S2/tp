package seedu.duke.states;

import seedu.duke.exceptions.DukeError;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class ExerciseStateHandler {
    private ArrayList<ExerciseData> currentSessionWorkout = new ArrayList<>();
    private static ArrayList<ExerciseData> previousGeneratedWorkout = new ArrayList<>();
    public boolean workoutOngoing;

    public void storePreviousGeneratedWorkout(ArrayList<ExerciseData> previousWorkout){
        previousGeneratedWorkout = previousWorkout;
    }

    public void startWorkout(){
        System.out.println("Start workout");
        currentSessionWorkout = previousGeneratedWorkout;
        workoutOngoing = true;
    }

    public void printCurrentWorkout() throws DukeError {
        if (workoutOngoing == false){
            throw new DukeError("There is no current workout session!");
        }
        Ui ui = new Ui();
        ui.printExerciseFromList(currentSessionWorkout);
    }


    public void endWorkout(boolean workoutCompleted){
        workoutOngoing = false;
        if (workoutCompleted) {
            //save into storage
            saveWorkout(currentSessionWorkout);
        }
        currentSessionWorkout = null;

    }

    private static void saveWorkout(ArrayList<ExerciseData> completedWorkout){
        System.out.println("Workout completed");
        //complete workout
    }
}
