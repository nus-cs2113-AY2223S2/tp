package seedu.duke.commands;

import seedu.duke.errors.DukeError;
import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class GenerateSpecificDifficultyCommand extends Command {

    private static final String EASY = "easy";
    private static final String MEDIUM = "medium";
    private static final String HARD = "hard";

    private static final String BEGINNER = "beginner";
    private static final String INTERMEDIATE = "intermediate";
    private static final String EXPERT = "expert";
    private final String difficulty;
    private final int count;

    public GenerateSpecificDifficultyCommand(String difficulty, String userInput) throws DukeError {
        try{
            this.count = Integer.parseInt(userInput);
        } catch( NumberFormatException e){
            throw new DukeError("You did not input a number as the number of exercises to generate");
        }
        if (difficulty.equals(EASY)) {
            this.difficulty = BEGINNER;
        } else if ((difficulty.equals(MEDIUM))) {
            this.difficulty = INTERMEDIATE;
        } else if ((difficulty.equals(HARD))) {
            this.difficulty = EXPERT;
        } else {
            throw new DukeError("You did not put in the correct format for generating a specific difficulty list");
        }
    }

    public void executeCommand(Ui ui, GenerateExercise exerciseGenerator) {
        ArrayList<ExerciseData> exercises = exerciseGenerator.generateSpecificDifficultySet(difficulty, count);
        ui.printExerciseFromList(exercises);
    }
}
