package seedu.duke.commands;

import seedu.duke.Utility.DifficultyLevel;
import seedu.duke.errors.DukeError;
import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class GenerateSpecificDifficultyCommand extends Command {
    private final String difficulty;
    private final String EASY = "easy";
    private final String MEDIUM = "medium";
    private final String HARD = "hard";

    private final String BEGINNER = "beginner";
    private final String INTERMEDIATE = "intermediate";
    private final String EXPERT = "expert";
    private final int count;

    public GenerateSpecificDifficultyCommand(String difficulty, String userInput) throws DukeError {
        System.out.println(userInput);
        this.count = Integer.parseInt(userInput);
        if (difficulty.equals(EASY)){
            this.difficulty = BEGINNER;
        }
        else if ((difficulty.equals(MEDIUM))){
            this.difficulty = INTERMEDIATE;
        }
        else if ((difficulty.equals(HARD))){
            this.difficulty = EXPERT;
        }
        else {
            this.difficulty = null;
            throw new DukeError("You did not put in the correct format for generating a specific difficulty list");
        }

    }


    public void executeCommand(Ui ui, GenerateExercise exerciseGenerator){
        ArrayList<ExerciseData> exercises = exerciseGenerator.generateSpecificDifficultySet(count, difficulty);
        ui.printExerciseFromList(exercises);
    }
}
