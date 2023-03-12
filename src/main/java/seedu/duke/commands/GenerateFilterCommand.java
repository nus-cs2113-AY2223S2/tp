
package seedu.duke.commands;

import seedu.duke.errors.DukeError;
import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class GenerateFilterCommand extends Command {

    private static final String EASY = "easy";
    private static final String MEDIUM = "medium";
    private static final String HARD = "hard";
    private static final String GYM = "gym";
    private static final String STATIC = "static";


    private final int count;
    private final String[] userCommands;

    public GenerateFilterCommand(String[] userCommands) {
        this.count = userCommands.length;
        this.userCommands = userCommands;
    }

    public void executeCommand(Ui ui, GenerateExercise exerciseGenerator) {
        ArrayList<ExerciseData> exercises = exerciseGenerator.generateSetAll();
        int numberOfExercisesToGenerate = 0;
        try {
            for (int i = 1; i < count; i++) {
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
                // add more filters here!
                /*
                 * case "arms":
                 * exercises = filterByArms(exercises);
                 * break;
                 * case "legs":
                 * exercises = filterByLegs(exercises);
                 * break;
                 * case "core":
                 * exercises = filterByCore(exercises);
                 * break;
                 */
                default:
                    try {
                        numberOfExercisesToGenerate = Integer.parseInt(userCommands[i]);
                    } catch (NumberFormatException e) {
                        throw new DukeError("enter a valid filter! type [filters] to see what filters exist!");
                    }
                }
            }
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        exercises = exerciseGenerator.generateRandomSetFrom(exercises, numberOfExercisesToGenerate);
        ui.printExerciseFromList(exercises);
    }
}
