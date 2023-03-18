
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

    private ArrayList<ExerciseData> exerciseListGenerated;


    /**
     * Parses the user input into data required
     * for generating an exercise
     * @param userCommands
     * @throws DukeError
     */
    public GenerateFilterCommand(String[] userCommands) throws DukeError {
        this.filterArguments = userCommands.length - 1;
        this.userCommands = userCommands;
        String userGenerateCount = userCommands[userCommands.length - 1];
        try {
            this.numberOfExercisesToGenerate = Integer.parseInt(userGenerateCount);
        } catch (NumberFormatException error) {
            throw new DukeError("Invalid input! Please enter the number of exercises you want!");
        }
    }

    /**
     * Filters the whole list of available exercises based off the
     * input from the user.
     * @param ui Prints out the respective exercises for a given input
     * @param exerciseGenerator Generates Exercises
     * @throws DukeError
     */
    public void executeCommand(Ui ui, GenerateExercise exerciseGenerator) throws DukeError {
        ArrayList<ExerciseData> exercises = new ArrayList<>(exerciseGenerator.generateSetAll());
        assert System.identityHashCode(exercises) != System.identityHashCode(exerciseGenerator.generateSetAll())
                : "Do not modify the ArrayList of GenerateExercise";
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
        if (numberOfExercisesToGenerate > exercises.size()) {
            throw new FilterTooManyError();
        }
        exercises = exerciseGenerator.generateRandomSetFrom(exercises, numberOfExercisesToGenerate);
        exerciseListGenerated = exercises;
        ui.printExerciseFromList(exercises);
    }

    public ArrayList<ExerciseData> provideExerciseList() {
        return exerciseListGenerated;
    }
}
