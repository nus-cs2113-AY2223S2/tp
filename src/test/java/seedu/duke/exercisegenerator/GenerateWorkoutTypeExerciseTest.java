package seedu.duke.exercisegenerator;

import org.junit.jupiter.api.Test;
import seedu.duke.Exceptions.DukeError;
import seedu.duke.exersisedata.ExerciseData;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GenerateWorkoutTypeExerciseTest {
    @Test
    void testUpperBodyWorkout() {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();

        exerciseData = generateExercise.generateSetAll();
        try{
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, "upper");
        } catch (DukeError e){
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++){
            String workoutType = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutType.indexOf("[");
            int end = workoutType.indexOf("]");
            String workoutTypeFinal = workoutType.substring(start+1,end);
            assertEquals(workoutTypeFinal, "upper body");
        }
    }

    @Test
    void testCoreWorkout() {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();

        exerciseData = generateExercise.generateSetAll();
        try{
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, "core");
        } catch (DukeError e){
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++){
            String workoutType = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutType.indexOf("[");
            int end = workoutType.indexOf("]");
            String workoutTypeFinal = workoutType.substring(start+1,end);
            assertEquals(workoutTypeFinal, "core");
        }
    }

    @Test
    void testLegsWorkout() {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();

        exerciseData = generateExercise.generateSetAll();
        try{
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, "legs");
        } catch (DukeError e){
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++){
            String workoutType = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutType.indexOf("[");
            int end = workoutType.indexOf("]");
            String workoutTypeFinal = workoutType.substring(start+1,end);
            assertEquals(workoutTypeFinal, "legs");
        }
    }

    @Test
    void testMixture() {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();

        exerciseData = generateExercise.generateSetAll();
        try{
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, "core");
            exerciseData = generateExercise.generateFilteredBodySetFrom(exerciseData);
        } catch (DukeError e){
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++){
            String workoutType = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutType.indexOf("[");
            int end = workoutType.indexOf("]");
            String workoutTypeFinal = workoutType.substring(start+1,end);
            assertEquals(workoutTypeFinal, "core");
            assertNotEquals(exerciseData.get(i).getEquipment(), null);
            assertEquals(exerciseData.get(i).getEquipment(), "body only");
        }
    }
}
