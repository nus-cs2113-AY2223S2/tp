package seedu.duke.logic.commands;

import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.ui.ErrorMessages;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.StringJoiner;

public class ExerciseSearchCommand extends Command {
    //@@author ghzr0
    private String userQuery = "";

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
        } else {
            StringJoiner stringJoiner = new StringJoiner(" ");
            for (int i = 1; i < userCommands.length; i++) {
                stringJoiner.add(userCommands[i]);
            }
            userQuery = stringJoiner.toString();
        }
    }

    /**
     * Filters the whole list of available exercises based off the
     * input from the user.
     *
     * @param ui Prints out the respective exercises for a given keyword
     * @param exerciseGenerator Generates Exercise List with the relevant keyword
     */
    public void executeCommand (Ui ui, GenerateExercise exerciseGenerator) throws DukeError {
        ArrayList<ExerciseData> exercisesList = new GenerateExercise().generateSetAll();
        ArrayList<String> matchingList = new ArrayList<>();
        assert exercisesList != null : "exercisesList should not be null.";
        int index = 1;

        for (ExerciseData exercise : exercisesList) {
            if (exercise.getName().toLowerCase().contains(userQuery) || exercise.getName().contains(userQuery)) {
                matchingList.add(exercise.getName());
            }
        }
        for (String exName : matchingList){
            System.out.println(index + "." + exName);
            index++;
        }
        if (matchingList.isEmpty()){
            throw new DukeError(ErrorMessages.ERROR_NO_MATCHING_KEYWORD.toString());
        }
    }
}
