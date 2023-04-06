package seedu.duke.exercisegenerator;

import org.junit.jupiter.api.Test;
import seedu.duke.logic.commands.GenerateFilterCommand;
import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;

import java.util.ArrayList;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author EangJS
public class GenerateExerciseTest {
    private static final long RANDOM_SEED = 2787311;

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
     * Tests an extensive usage of the application by generating a large number of sets for the user with given filters.
     * The number of sets generated should be sufficient and should not throw any exceptions
     */
    @Test
    void testGenerateCommand () throws DukeError {
        String sampleUserCommand = "upper medium 99";
        GenerateFilterCommand generateFilterCommand = new GenerateFilterCommand(sampleUserCommand.split(" "));
        Ui ui = new Ui();
        GenerateExercise generateExercise = new GenerateExercise(RANDOM_SEED);
        assertDoesNotThrow(() -> generateFilterCommand.executeCommand(ui, generateExercise), "GenerateFilterCommand " +
            "throws an error");
        generateFilterCommand.executeCommand(ui, generateExercise);
    }

    /**
     * Tests an edge limit of generating a very large number of test. This acts as a stress test to ensure code does
     * not break given a large input.
     *
     * @throws DukeError Occurs when there is a file read error from data.json.
     */
    @Test
    void testGenerateCommandLargeSet () throws DukeError {
        String sampleUserCommand = "medium legs 9999999";
        GenerateFilterCommand generateFilterCommand = new GenerateFilterCommand(sampleUserCommand.split(" "));
        Ui ui = new Ui();
        GenerateExercise generateExercise = new GenerateExercise(RANDOM_SEED);
        assertThrows(DukeError.class, () -> generateFilterCommand.executeCommand(ui, generateExercise));
    }

    /**
     * Tests another edge limit of having multiple filters that are mutually exclusive in criterion such that there are
     * no exercises which simultaneously have the same criterion and should throw an appropriate error, in this case
     * a TooManyFiltersError.
     *
     * @throws DukeError Occurs when there is a file read error from data.json.
     */
    @Test
    void testGenerateCommandManyFilter () throws DukeError {
        String sampleUserCommand = "medium legs core upper 100";
        GenerateFilterCommand generateFilterCommand = new GenerateFilterCommand(sampleUserCommand.split(" "));
        Ui ui = new Ui();
        GenerateExercise generateExercise = new GenerateExercise(RANDOM_SEED);
        assertThrows(DukeError.class, () -> generateFilterCommand.executeCommand(ui, generateExercise));
    }

    /**
     * Tests a stress limit of an input larger than maximum size of an int which should throw a different error from
     * testGenerateCommandLargeSet.
     */
    @Test
    void testInvalidInput () {
        String sampleUserCommand = "easy core 9999999999999999999999999";
        assertThrows(DukeError.class,
                     () -> new GenerateFilterCommand(sampleUserCommand.split(" ")));

    }

}
