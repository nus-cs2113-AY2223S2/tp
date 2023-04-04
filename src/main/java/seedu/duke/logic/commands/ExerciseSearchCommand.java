package seedu.duke.logic.commands;

import java.util.StringJoiner;
import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.ui.ErrorMessages;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class ExerciseSearchCommand extends Command {
    //@@author ghzr0
    private static String keyword = "";

    /**
     * Parses the user input into data required
     * for generating the list of relevant exercises with the specified keyword
     *
     * @param userQuery
     * @throws DukeError (if user did not key in a keyword to search)
     */
    public ExerciseSearchCommand (String[] userQuery) throws DukeError {

        if (userQuery.length > 0) {
            StringJoiner joiner = new StringJoiner(" ");
            for (String wordQuery : userQuery) {
                joiner.add(wordQuery);
            }
            keyword = joiner.toString();
        } else {
            throw new DukeError(ErrorMessages.ERROR_EMPTY_KEYWORD.toString());
        }
    }
    //@@author ghzr0

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
        for (ExerciseData exercise : exercisesList) {
            if (exercise.getName().toLowerCase().contains(keyword)) {
                System.out.println(index + "." + exercise.getName());
                index++;
            }
        }
    }

}
