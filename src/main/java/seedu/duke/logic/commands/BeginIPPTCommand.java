package seedu.duke.logic.commands;

import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;
import seedu.duke.ui.ErrorMessages;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class BeginIPPTCommand extends Command{
    private String keyword = "";
    public BeginIPPTCommand(String userInput)throws DukeError{
        if (userInput.equals("ippt")) {

        } else {
            throw new DukeError(ErrorMessages.ERROR_EMPTY_KEYWORD.toString());
        }
    }
    public void executeCommand (Ui ui, GenerateExercise exerciseGenerator) throws DukeError {
        ArrayList<ExerciseData> exercises = new GenerateExercise().generateSetAll();
        ArrayList<ExerciseData> exercisesList = new GenerateExercise().generateIPPTExercises(exercises);



    }
}
