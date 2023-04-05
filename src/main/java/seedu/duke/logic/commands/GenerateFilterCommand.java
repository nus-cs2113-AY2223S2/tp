package seedu.duke.logic.commands;

import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;
import seedu.duke.data.userdata.UserCareerData;
import seedu.duke.ui.ErrorMessages;
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
    private static final String IPPT = "ippt";

    private final int filterArguments;
    private final String[] userCommands;
    private final int numberOfExercisesToGenerate;

    private ArrayList<ExerciseData> exerciseListGenerated;

    /**
     * Parses the user input into data required
     * for generating an exercise
     *
     * @param userCommands The parameters that the user wishes their exercises to have
     *     and includes the number of exercises to have.
     * @throws DukeError
     */
    public GenerateFilterCommand (String[] userCommands) throws DukeError {
        this.filterArguments = userCommands.length - 1;
        this.userCommands = userCommands;
        String userGenerateCount = userCommands[userCommands.length - 1];
        try {
            this.numberOfExercisesToGenerate = Integer.parseInt(userGenerateCount);
            if (this.numberOfExercisesToGenerate <= 0) {
                throw new DukeError(ErrorMessages.ERROR_EXERCISE_NUM_INPUT_STRING.toString());
            }
        } catch (NumberFormatException error) {
            throw new DukeError(ErrorMessages.ERROR_EXERCISE_NUM_INPUT_STRING.toString());
        }
    }

    //@@author Khulon

    /**
     * Filters the whole list of available exercises based off the
     * input from the user.
     *
     * @param ui Prints out the respective exercises for a given input
     * @param exerciseGenerator Generates Exercises
     * @throws DukeError duke error
     */

    //test
    //public void executeCommand (Ui ui, GenerateExercise exerciseGenerator) throws DukeError {
    public void executeCommand (Ui ui, GenerateExercise exerciseGenerator) throws DukeError {
        ArrayList<ExerciseData> exercises = new ArrayList<>(exerciseGenerator.generateSetAll());
        ArrayList<ExerciseData> ipptList = new ArrayList<>(exerciseGenerator.generateIPPTExercises(exercises));
        assert System.identityHashCode(exercises) != System.identityHashCode(exerciseGenerator.generateSetAll())
            : "Do not modify the ArrayList of GenerateExercise";
        if(userCommands.length == 1 && userCommands.equals(IPPT)){
            exerciseGenerator.generateIPPTExercises(ipptList);
        }else{
            for (int i = 1; i < filterArguments; i++) {
                switch (userCommands[i]) {
                case GYM:
                    exercises = exerciseGenerator.generateFilteredGymSetFrom(exercises);
                    break;
                case STATIC:
                    exercises = exerciseGenerator.generateFilteredStaticSetFrom(exercises);
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
                    throw new DukeError(ErrorMessages.ERROR_FILTER_INPUT.toString());
                }
            }
        }
        if (numberOfExercisesToGenerate == 1337) {
            exercises = exerciseGenerator.generateFirstThree();
        } else if (numberOfExercisesToGenerate > exercises.size()) {
            throw new DukeError(ErrorMessages.ERROR_EXCESSIVE_FILTERS.toString());
        } else {
            exercises = exerciseGenerator.generateRandomSetFrom(exercises, numberOfExercisesToGenerate);
        }
        exerciseListGenerated = exercises;
        ui.printExerciseFromList(exercises);
    }


    public static boolean isAValidSetOfFilters (GenerateExercise exerciseGenerator,
                                                String[] filterList) throws DukeError {
        ArrayList<ExerciseData> exercises = new ArrayList<>(exerciseGenerator.generateSetAll());
        assert System.identityHashCode(exercises) != System.identityHashCode(exerciseGenerator.generateSetAll())
            : "Do not modify the ArrayList of GenerateExercise";
        for (int i = 0; i < filterList.length; i++) {
            switch (filterList[i]) {
            case GYM:
                exercises = exerciseGenerator.generateFilteredGymSetFrom(exercises);
                break;
            case STATIC:
                exercises = exerciseGenerator.generateFilteredStaticSetFrom(exercises);
                break;
            case EASY:
            case MEDIUM:
            case HARD:
                exercises = exerciseGenerator.generateFilteredDifficultySetFrom(exercises, filterList[i]);
                break;
            case UPPER:
            case CORE:
            case LEGS:
                exercises = exerciseGenerator.generateFilteredWorkoutTypeFrom(exercises, filterList[i]);
                break;
            default:
                throw new DukeError(ErrorMessages.ERROR_FILTER_INPUT.toString());
            }
        }

        if (exercises.size() == 0) {
            throw new DukeError(ErrorMessages.ERROR_EXCESSIVE_FILTERS.toString());
        } else {
            return true;
        }
    }


    //@author

    public ArrayList<ExerciseData> provideExerciseList () {
        return exerciseListGenerated;
    }

}
