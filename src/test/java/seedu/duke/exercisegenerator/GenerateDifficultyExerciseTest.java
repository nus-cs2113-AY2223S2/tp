package seedu.duke.exercisegenerator;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.GenerateSpecificDifficultyCommand;
import seedu.duke.errors.DukeError;
import seedu.duke.exersisedata.ExerciseData;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenerateDifficultyExerciseTest {
    @Test
    void testDifficultyExerciseLength() {
        GenerateExercise generateExercise = new GenerateExercise();
        ArrayList<ExerciseData> currentTestList = generateExercise.generateSpecificDifficultySet("beginner", 4);

        assertEquals(4, currentTestList.size());
        assertEquals(4, generateExercise.generateSpecificDifficultySet("intermediate", 4).size());
        assertEquals(4, generateExercise.generateSpecificDifficultySet("expert", 4).size());



    }

    @Test
    void testCorrectDifficultyLevel() {
        GenerateExercise generateExercise = new GenerateExercise();
        //beginner tests
        ArrayList<ExerciseData> currentTestList;
        currentTestList = generateExercise.generateSpecificDifficultySet("beginner", 5);
        for (int i = 0; i < currentTestList.size(); i++) {
            assertEquals(currentTestList.get(i).getLevel(), "beginner");
        }

        //intermediate
        currentTestList = generateExercise.generateSpecificDifficultySet("intermediate", 5);
        for (int i = 0; i < currentTestList.size(); i++) {
            assertEquals(currentTestList.get(i).getLevel(), "intermediate");
        }

        //expert tests
        currentTestList = generateExercise.generateSpecificDifficultySet("expert", 5);
        for (int i = 0; i < currentTestList.size(); i++) {
            assertEquals(currentTestList.get(i).getLevel(), "expert");
        }
    }

    /**
     * Checks whether the correct errors are caught
     * Errors include:
     * 1) Incorrect difficulty name
     * 2) Passing a string that is supposed to be an integer
     */
    @Test
    void testIncorrectInputExceptionHandling() {
        //error check if incorrect input
        Exception exception = assertThrows(DukeError.class, () -> {
            new GenerateSpecificDifficultyCommand("beginner", "5");
        });
        String expectedMessage = "You did not put in the correct format for generating a specific difficulty list";
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.equals(actualMessage));

        exception = assertThrows(DukeError.class, () -> {
            new GenerateSpecificDifficultyCommand("easy", "blabla");
        });
        expectedMessage = "You did not input a number as the number of exercises to generate";
        actualMessage = exception.getMessage();
        assertTrue(expectedMessage.equals(actualMessage));
    }
}
