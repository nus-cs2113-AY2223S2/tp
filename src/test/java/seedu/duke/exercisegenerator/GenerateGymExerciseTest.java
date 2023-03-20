package seedu.duke.exercisegenerator;

import org.junit.jupiter.api.Test;
import seedu.duke.exersisedata.ExerciseData;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Checks if program accurately filters gym exercises from entire
 * list of data
 */
public class GenerateGymExerciseTest {
    private static final String BODY = "body only";
    @Test
    void testGymWorkout() {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();

        exerciseData = generateExercise.generateSetAll();
        exerciseData = generateExercise.generateFilteredGymSetFrom(exerciseData);

        for (int i = 0; i < exerciseData.size(); i++){
            assertNotEquals(exerciseData.get(i).getEquipment(), null);
            assertNotEquals(exerciseData.get(i).getEquipment(), BODY);
        }
    }

}
