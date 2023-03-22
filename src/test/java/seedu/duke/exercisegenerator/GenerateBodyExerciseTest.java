package seedu.duke.exercisegenerator;

import org.junit.jupiter.api.Test;
import seedu.duke.exersisedata.ExerciseData;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GenerateBodyExerciseTest {
    private static String BODY = "body only";

    /**
     * Checks if program accurately filters body weight exercises from
     * entire list of data
     */
    @Test
    void testBodyWorkout() {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();

        exerciseData = generateExercise.generateSetAll();
        exerciseData = generateExercise.generateFilteredStaticSetFrom(exerciseData);

        for (int i = 0; i < exerciseData.size(); i++){
            assertNotEquals(exerciseData.get(i).getEquipment(), null);
            assertEquals(exerciseData.get(i).getEquipment(), BODY);
        }
    }
}
