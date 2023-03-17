package seedu.duke.commands;

import java.util.ArrayList;
import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.storage.UserCareerData;
import seedu.duke.storage.WriteUserData;
import seedu.duke.ui.Ui;
import seedu.duke.userdata.Session;

public class SampleSavingCommand extends Command {
    private final Session session;
    private UserCareerData userCareerData;

    public SampleSavingCommand (UserCareerData userCareerData, ArrayList<ExerciseData> sessionExercises) {
        this.session = new Session(sessionExercises);
        this.userCareerData = userCareerData;
        this.userCareerData.addWorkoutSession(session);
    }

    public void executeCommand (Ui ui, GenerateExercise exerciseGenerator) {
        WriteUserData.writeToJson("userData.json", this.userCareerData);
        System.out.println("I have written:");
        for (var i : session.getSessionExercises()) {
            System.out.println(i.getName());
        }
        System.out.println("To userData.json");
    }

}
