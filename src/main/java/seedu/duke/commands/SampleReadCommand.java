package seedu.duke.commands;

import seedu.duke.exceptions.DukeError;
import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.storage.UserCareerData;
import seedu.duke.ui.Ui;
import seedu.duke.userdata.Session;

public class SampleReadCommand extends Command {
    private UserCareerData userCareerData;

    /**
     * Sets the userCareerData as a local variable for use later
     *
     * @param userCareerData
     */

    public SampleReadCommand (UserCareerData userCareerData) {
        this.userCareerData = userCareerData;
    }

    /**
     * Simply prints the getName() of each exercise in session
     *
     * @param ui Ui class to handle all ui related events
     * @param exerciseGenerator generates exercises
     * @throws DukeError occurs when there is an invalid command
     */
    public void executeCommand (Ui ui, GenerateExercise exerciseGenerator) throws DukeError {
        for (Session session : userCareerData.getTotalUserCareerSessions()) {
            for (ExerciseData exercise : session.getSessionExercises()) {
                System.out.println(exercise.getName());
            }
        }
    }

}
