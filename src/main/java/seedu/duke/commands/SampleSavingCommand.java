package seedu.duke.commands;

import java.util.ArrayList;

import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.storage.UserCareerData;
import seedu.duke.storage.UserDataWriter;
import seedu.duke.ui.Ui;
import seedu.duke.userdata.Session;

public class SampleSavingCommand extends Command {
    private final Session session;
    private UserCareerData userCareerData;

    /**
     * Sets the updated / appended userCareerData by appending a newly created session.class from arraylist of
     * exercises (session exercises)
     *
     * @param userCareerData all previous user career data
     * @param sessionExercises the exercises completed in the current session to be added
     */

    public SampleSavingCommand (UserCareerData userCareerData, ArrayList<ExerciseData> sessionExercises) {
        this.session = new Session(sessionExercises);
        this.userCareerData = userCareerData;
        this.userCareerData.addWorkoutSession(session);
    }

    /**
     * Writes the UserCareerData to json file and prints the exercises added as a confirmatory message
     *
     * @param ui handles all ui events (to be used later)
     * @param exerciseGenerator (unused here)
     */
    public void executeCommand (Ui ui, GenerateExercise exerciseGenerator) {
        UserDataWriter.writeToJson("userData.json", this.userCareerData);
        System.out.println("I have written:");
        for (var i : session.getSessionExercises()) {
            System.out.println(i.getName());
        }
        System.out.println("To userData.json");
    }

}
