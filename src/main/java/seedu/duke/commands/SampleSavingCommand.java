package seedu.duke.commands;

import java.util.ArrayList;

import seedu.duke.exceptions.DukeError;
import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.storage.StorageHandler;
import seedu.duke.userdata.UserCareerData;
import seedu.duke.ui.Ui;
import seedu.duke.userdata.Session;

public class SampleSavingCommand extends Command {
    private final Session session;
    private UserCareerData userCareerData;
    private StorageHandler storageHandler;

    /**
     * Sets the updated / appended userCareerData by appending a newly created session.class from arraylist of
     * exercises (session exercises)
     *
     * @param userCareerData all previous user career data
     * @param sessionExercises the exercises completed in the current session to be added
     */

    public SampleSavingCommand (UserCareerData userCareerData, ArrayList<ExerciseData> sessionExercises,
                                StorageHandler storageHandler) {
        this.session = new Session(sessionExercises);
        this.userCareerData = userCareerData;
        this.userCareerData.addWorkoutSession(session);
        this.storageHandler = storageHandler;
    }

    /**
     * Writes the UserCareerData to json file and prints the exercises added as a confirmatory message
     *
     * @param ui handles all ui events (to be used later)
     * @param exerciseGenerator (unused here)
     */
    public void executeCommand (Ui ui, GenerateExercise exerciseGenerator) throws DukeError {
        storageHandler.writeToJson(this.userCareerData);
        System.out.println("I have written:");
        for (ExerciseData i : session.getSessionExercises()) {
            System.out.println(i.getName());
        }
        System.out.println("To userData.json");
    }

}
