package seedu.duke.commands;

import seedu.duke.exceptions.DukeError;
import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.storage.UserCareerData;
import seedu.duke.ui.Ui;
import seedu.duke.userdata.Session;

public class SampleReadCommand extends Command {
    private UserCareerData userCareerData;

    public SampleReadCommand (UserCareerData userCareerData) {
        this.userCareerData = userCareerData;
    }

    public void executeCommand (Ui ui, GenerateExercise exerciseGenerator) throws DukeError {
        for (Session session : userCareerData.getTotalUserCareerSessions()) {
            for (ExerciseData exercise : session.getSessionExercises()) {
                System.out.println(exercise.getName());
            }
        }
    }

}
