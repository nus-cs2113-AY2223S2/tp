package seedu.duke.commands;

import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.userdata.UserCareerData;
import seedu.duke.ui.Ui;
import seedu.duke.userdata.Session;

public class SampleReadCommand extends Command {
    private final UserCareerData userCareerData;

    /**
     * Sets the userCareerData as a local variable for use later
     *
     * @param userCareerData Data of sessions completed by user
     */

    public SampleReadCommand (UserCareerData userCareerData) {
        this.userCareerData = userCareerData;
    }

    /**
     * Simply prints the getName() of each exercise in session
     *
     * @param ui Ui class to handle all ui related events
     * @param exerciseGenerator generates exercises
     */
    public void executeCommand (Ui ui, GenerateExercise exerciseGenerator) {
        for (Session session : userCareerData.getTotalUserCareerSessions()) {
            for (ExerciseData exercise : session.getSessionExercises()) {
                System.out.println(exercise.getName());
            }
        }
    }

}
