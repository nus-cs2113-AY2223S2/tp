package seedu.duke.logic.commands;

import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.ui.ErrorMessages;
import seedu.duke.ui.Ui;

import java.util.ArrayList;


public class ExerciseSearchCommand extends Command {
    //@@author ghzr0
    private String words = "";
    /**
     * Parses the user input into data required
     * for generating the list of relevant exercises with the specified keyword
     *
     * @param userCommands
     * @throws DukeError (if user did not key in a keyword to search)
     */
    public ExerciseSearchCommand (String[] userCommands) throws DukeError {
        if (userCommands.length == 1) {
            throw new DukeError(ErrorMessages.ERROR_EMPTY_KEYWORD.toString());
        }else{
            StringBuffer sb = new StringBuffer();
            for (int i = 1; i < userCommands.length; i++){
                sb.append(userCommands[i]);
            }
            words = sb.toString();
        }
    }
    /**
     * Filters the whole list of available exercises based off the
     * input from the user.
     *
     * @param ui Prints out the respective exercises for a given keyword
     * @param exerciseGenerator Generates Exercise List with the relevant keyword
     */
    public void executeCommand (Ui ui, GenerateExercise exerciseGenerator) {
        ArrayList<ExerciseData> exercisesList = new GenerateExercise().generateSetAll();
        assert exercisesList != null : "exercisesList should not be null.";
        int index = 1;

        System.out.println("Here are the exercises matching your keyword:");
        for(ExerciseData exercise : exercisesList) {
            if (exercise.getName().toLowerCase().contains(words)||exercise.getName().contains(words)) {
                System.out.println(index + "." + exercise.getName());
                index++;
            }
        }
    }
}
