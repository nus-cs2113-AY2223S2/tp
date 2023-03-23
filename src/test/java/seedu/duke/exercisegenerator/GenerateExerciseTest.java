package seedu.duke.exercisegenerator;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.GenerateFilterCommand;
import seedu.duke.exceptions.DukeError;
import seedu.duke.exersisedata.ExerciseData;

import java.util.ArrayList;
import seedu.duke.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GenerateExerciseTest {
    private final long RANDOM_SEED = 2787311;

    /**
     * Checks if able to generate exercise list of size equal to count
     */
    @Test
    void testGeneration () {
        GenerateExercise generateExercise = new GenerateExercise();
        assertEquals(5, generateExercise.generateRandomSetFrom(generateExercise.generateSetAll(), 5).size());
        assertEquals(0, generateExercise.generateRandomSetFrom(generateExercise.generateSetAll(), 0).size());
        assertEquals(0, generateExercise.generateRandomSetFrom(generateExercise.generateSetAll(), -1).size());
    }

    /**
     * Checks if ArrayList contains exercise data
     */
    @Test
    void testData () {
        GenerateExercise generateExercise = new GenerateExercise();
        ArrayList<ExerciseData> exerciseDatas = generateExercise.generateRandomSetFrom(
            generateExercise.generateSetAll(), 5);
        assertNotNull(exerciseDatas.get(0));
    }

    /**
     * Test to check if given the same random seed, program should be able to output the same data
     */
    @Test
    void testRandomSet () {
        GenerateExercise generateExercise = new GenerateExercise(RANDOM_SEED);
        ArrayList<ExerciseData> exerciseData = generateExercise.generateRandomSetFrom(
            generateExercise.generateSetAll(), 500);
        GenerateExercise generateExerciseTest = new GenerateExercise(RANDOM_SEED);
        ArrayList<ExerciseData> testExerciseData = generateExerciseTest.generateRandomSetFrom(
            generateExerciseTest.generateSetAll(), 500);
        for (int i = 0; i < exerciseData.size(); i++) {
            assertEquals(exerciseData.get(i).getId(), testExerciseData.get(i).getId(), "Exercise ID Mismatch");
            assertEquals(exerciseData.get(i).getName(), testExerciseData.get(i).getName(), "Exercise Name Mismatch");
        }
    }

    /**
     *
     */
    @Test
    void testGenerateCommand () throws DukeError {
        String sampleUserCommand = "upper medium 99";
        GenerateFilterCommand generateFilterCommand = new GenerateFilterCommand(sampleUserCommand.split(" "));
        Ui ui = new Ui();
        GenerateExercise generateExercise = new GenerateExercise(RANDOM_SEED);
        assertDoesNotThrow(generateFilterCommand.executeCommand(ui, generateExercise));
        generateFilterCommand.executeCommand(ui, generateExercise);
    }

}
