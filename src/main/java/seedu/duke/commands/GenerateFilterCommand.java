
package seedu.duke.commands;

import seedu.duke.exceptions.DukeError;
import seedu.duke.exceptions.FilterTooManyError;
import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class GenerateFilterCommand extends Command {

    private static final String GYM = "gym";
    private static final String STATIC = "static";
    private static final String EASY = "easy";
    private static final String MEDIUM = "medium";
    private static final String HARD = "hard";
    private static final String UPPER = "upper";
    private static final String CORE = "core";
    private static final String LEGS = "legs";

    private final int filterArguments;
    private final String[] userCommands;
    private final int numberOfExercisesToGenerate;

    public GenerateFilterCommand(String[] userCommands) throws DukeError {
        this.filterArguments = userCommands.length - 1;
        this.userCommands = userCommands;
        String userGenerateCount = userCommands[userCommands.length-1];
        try{
            this.numberOfExercisesToGenerate = Integer.parseInt(userGenerateCount);
        } catch (NumberFormatException error){
            throw new DukeError("Invalid input! Please enter the number of exercises you want!");
        }
    }

    public void executeCommand(Ui ui, GenerateExercise exerciseGenerator) throws DukeError {
        ArrayList<ExerciseData> exercises = exerciseGenerator.generateSetAll();
        for (int i = 1; i < filterArguments; i++) {
            switch (userCommands[i]) {
            case GYM:
                exercises = exerciseGenerator.generateFilteredGymSetFrom(exercises);
                break;
            case STATIC:
                exercises = exerciseGenerator.generateFilteredBodySetFrom(exercises);
                break;
            case EASY:
            case MEDIUM:
            case HARD:
                exercises = exerciseGenerator.generateFilteredDifficultySetFrom(exercises, userCommands[i]);
                break;
            case UPPER:
            case CORE:
            case LEGS:
                exercises = exerciseGenerator.generateFilteredWorkoutTypeFrom(exercises, userCommands[i]);
                break;
            default:
                throw new DukeError("Unknown filter input!");
            }
        }
        if(numberOfExercisesToGenerate > exercises.size()){
            throw new FilterTooManyError();
        }
        exercises = exerciseGenerator.generateRandomSetFrom(exercises, numberOfExercisesToGenerate);
        ui.printExerciseFromList(exercises);
    }
}
