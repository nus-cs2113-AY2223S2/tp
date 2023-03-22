package seedu.duke.exercisegenerator;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeError;
import seedu.duke.exersisedata.ExerciseData;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GenerateDifficultyExerciseTest {

    @Test
    void testBeginnerDifficultyLevel() {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();

        exerciseData = generateExercise.generateSetAll();
        try{
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, "easy");
        } catch (DukeError e){
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++){
            assertEquals(exerciseData.get(i).getLevel(), "beginner");
        }
    }

    @Test
    void testIntermediateDifficultyLevel() {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();

        exerciseData = generateExercise.generateSetAll();
        try{
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, "medium");
        } catch (DukeError e){
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++){
            assertEquals(exerciseData.get(i).getLevel(), "intermediate");
        }
    }

    @Test
    void testExpertDifficultyLevel() {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();

        exerciseData = generateExercise.generateSetAll();
        try{
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, "hard");
        } catch (DukeError e){
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++){
            assertEquals(exerciseData.get(i).getLevel(), "expert");
        }
    }

    @Test
    void testMixture() {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();

        exerciseData = generateExercise.generateSetAll();
        try{
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, "medium");
            exerciseData = generateExercise.generateFilteredStaticSetFrom(exerciseData);
        } catch (DukeError e){
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++){
            assertEquals(exerciseData.get(i).getLevel(), "intermediate");
            assertNotEquals(exerciseData.get(i).getEquipment(), null);
            assertEquals(exerciseData.get(i).getEquipment(), "body only");
        }
    }

}
