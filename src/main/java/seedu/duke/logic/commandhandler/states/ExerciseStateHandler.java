package seedu.duke.logic.commandhandler.states;

import seedu.duke.achievements.Achievement;
import seedu.duke.achievements.AchievementListHandler;
import seedu.duke.achievements.types.AchievementBodyPart;
import seedu.duke.achievements.types.AchievementGymStatic;
import seedu.duke.achievements.types.AchievementLevel;
import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;
import seedu.duke.storage.Storage;
import seedu.duke.data.userdata.UserCareerData;
import seedu.duke.ui.ErrorMessages;
import seedu.duke.data.userdata.Session;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
//@@author ChubbsBunns

/**
 * This class handles the functions of the Fitness Duke
 * when the user is doing a workout
 */
public class ExerciseStateHandler {

    private static ArrayList<ExerciseData> previousGeneratedWorkout = new ArrayList<>();
    public boolean workoutOngoing;
    private Session currentSessionWorkout;
    private Storage storage;

    public ExerciseStateHandler (Storage storage) {
        this.storage = storage;
        this.currentSessionWorkout = new Session(null);
    }

    private static void printCancelWorkoutSessionMessage () {
        System.out.println("Workout cancelled, you can complete it next time!");
    }

    /**
     * This function logs the previous workout everytime a workout is generated
     * (In other words, whenever the generate command is called)
     *
     * @param previousWorkout Temporarily logs the most recent generated exercise
     *     list
     */
    public void storePreviousGeneratedWorkout (ArrayList<ExerciseData> previousWorkout) {
        assert previousWorkout != null;
        previousGeneratedWorkout = previousWorkout;
    }

    /**
     * This function switches the state of how Command Handler functions,
     * blocking off certain commands until the session has ended
     */
    public void startWorkout () throws DukeError {
        if (previousGeneratedWorkout.size() == 0) {
            throw new DukeError(ErrorMessages.ERROR_NO_EXERCISE_LOADED.toString());
        }
        System.out.println("The current workout is: ");
        System.out.println("The size of the current workout session is " + previousGeneratedWorkout.size());
        for (int i = 0; i < previousGeneratedWorkout.size(); i++) {
            System.out.println(previousGeneratedWorkout.get(i).getName());
        }

        System.out.println("Start workout! You got this, all the best!");
        currentSessionWorkout = new Session(previousGeneratedWorkout);
        workoutOngoing = true;
    }

    /**
     * Prints the current workout if it exists
     * Otherwise throws an error
     *
     * @throws DukeError Throws an error if there is no ongoing exercise
     *     session
     */
    public void printCurrentWorkout () throws DukeError {
        if (!workoutOngoing) {
            throw new DukeError(ErrorMessages.ERROR_NO_ONGOING_EXERCISE.toString());
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
    public void endWorkout (boolean workoutCompleted, UserCareerData userCareerData,
                            AchievementListHandler achievementListHandler) throws DukeError {
        assert userCareerData != null;
        workoutOngoing = false;
        if (workoutCompleted) {
            saveWorkoutSession(currentSessionWorkout, userCareerData);
            updateWorkoutAchievements(currentSessionWorkout, achievementListHandler);
        } else {
            printCancelWorkoutSessionMessage();
        }
        currentSessionWorkout = null;
    }

    //@@author L-K-Chng
    /**
     * Deletes the completed workout session which the user specifies.
     *
     * @param userCareerData Stores and contains user data.
     * @param i The number of the session which the user wishes to delete.
     * @throws DukeError
     */
    public void deleteWorkoutSession (UserCareerData userCareerData, int i) throws DukeError{
        System.out.println("OK, you have deleted Workout Session Number " + i + "!");
        userCareerData.deleteWorkoutSession(i);
        storage.writeToJson(userCareerData);
    }

    //@@author ChubbsBunns
    private void updateWorkoutAchievements (Session session, AchievementListHandler achievementListHandler) {
        ArrayList<Achievement> completedAchievements = new ArrayList<>();
        ArrayList<Achievement> loggedAchievements = achievementListHandler.getAchievementList();
        ArrayList<ExerciseData> exercises = session.getSessionExercises();
        for (int i = 0; i < loggedAchievements.size(); i++) {
            for (int j = 0; j < exercises.size(); j++) {
                ExerciseData exerciseData = exercises.get(j);
                if (loggedAchievements.get(i) instanceof AchievementGymStatic) {
                    AchievementGymStatic achievementGymStatic = (AchievementGymStatic) loggedAchievements.get(i);
                    if (achievementGymStatic.updateIndex(exerciseData)) {
                        completedAchievements.add(achievementGymStatic);
                    }
                }
                if (loggedAchievements.get(i) instanceof AchievementLevel) {
                    AchievementLevel achievementLevel = (AchievementLevel) loggedAchievements.get(i);
                    if (achievementLevel.updateIndex(exerciseData)) {
                        completedAchievements.add(achievementLevel);
                    }
                }
                if (loggedAchievements.get(i) instanceof AchievementBodyPart) {
                    AchievementBodyPart achievementBodyPart = (AchievementBodyPart) loggedAchievements.get(i);
                    if (achievementBodyPart.updateIndex(exerciseData)) {
                        completedAchievements.add(achievementBodyPart);
                    }
                }
            }
        }

        achievementListHandler.saveAchievementList();

        if (completedAchievements.size() != 0) {
            System.out.println("Congradulations! You have achieved the following achievements:");
            for (int i = 0; i < completedAchievements.size(); i++) {
                System.out.println((i + 1) + ") " + completedAchievements.get(i).getName() + ": " +
                                       completedAchievements.get(i).getRequirement());
            }
            System.out.println("Keep on working out with Fitness Duke!");
        }
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
        storage.writeToJson(userCareerData);
    }

}
