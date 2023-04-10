package seedu.duke.exercisegenerator;

import org.junit.jupiter.api.Test;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;

import java.util.ArrayList;
import seedu.duke.data.exercisegenerator.GenerateExercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GenerateBodyExerciseTest {
    private static final String BODY = "body only";

    /**
     * Checks if program accurately filters body weight exercises from
     * entire list of data
     */
    @Test
    void testBodyWorkout () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();

        exerciseData = generateExercise.generateSetAll();
        exerciseData = generateExercise.generateFilteredStaticSetFrom(exerciseData);

        for (int i = 0; i < exerciseData.size(); i++) {
            assertNotEquals(exerciseData.get(i).getEquipment(), null);
            assertEquals(exerciseData.get(i).getEquipment(), BODY);
        }
    }

}
