package seedu.duke.exercisegenerator;

import org.junit.jupiter.api.Test;
import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.model.exercisegenerator.exersisedata.ExerciseData;

import java.util.ArrayList;
import seedu.duke.model.exercisegenerator.GenerateExercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GenerateWorkoutTypeExerciseTest {
    private static final String UPPER = "upper";
    private static final String UPPER_BODY = "upper body";
    private static final String CORE = "core";
    private static final String LEGS = "legs";
    private static final String OPEN_BRACE = "[";
    private static final String CLOSE_BRACE = "]";
    private static final String BODY = "body only";
    private static final String EASY = "easy";
    private static final String BEGINNER = "beginner";

    /**
     * Checks if program accurately filters upper body exercises from entire
     * list of data
     */
    @Test
    void testUpperBodyWorkout () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();

        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, UPPER);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++) {
            String workoutType = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutType.indexOf(OPEN_BRACE);
            int end = workoutType.indexOf(CLOSE_BRACE);
            String workoutTypeFinal = workoutType.substring(start + 1, end);
            assertEquals(workoutTypeFinal, UPPER_BODY);
        }
    }

    /**
     * Checks if program accurately filters core exercises from entire
     * list of data
     */
    @Test
    void testCoreWorkout () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();

        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, CORE);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++) {
            String workoutType = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutType.indexOf(OPEN_BRACE);
            int end = workoutType.indexOf(CLOSE_BRACE);
            String workoutTypeFinal = workoutType.substring(start + 1, end);
            assertEquals(workoutTypeFinal, CORE);
        }
    }

    /**
     * Checks if program accurately filters legs exercises from entire
     * list of data
     */
    @Test
    void testLegsWorkout () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();

        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, LEGS);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++) {
            String workoutType = exerciseData.get(i).getWorkoutType().toString();
            //new stuff
            //assert workoutType == "core": "workoutType should be core";
            int start = workoutType.indexOf(OPEN_BRACE);
            int end = workoutType.indexOf(CLOSE_BRACE);
            String workoutTypeFinal = workoutType.substring(start + 1, end);
            assertEquals(workoutTypeFinal, LEGS);
        }
    }

    /**
     * Checks if program accurately filters both workout type and gym exercises
     * when ran together
     */
    @Test
    void testWorkoutTypeAndGymSet () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();

        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, CORE);
            exerciseData = generateExercise.generateFilteredGymSetFrom(exerciseData);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++) {
            String workoutType = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutType.indexOf(OPEN_BRACE);
            int end = workoutType.indexOf(CLOSE_BRACE);
            String workoutTypeFinal = workoutType.substring(start + 1, end);
            assertEquals(workoutTypeFinal, CORE);
            assertNotEquals(exerciseData.get(i).getEquipment(), BODY);
        }
    }

    /**
     * Checks if program accurately filters both workout type and exercises of
     * specific difficulty when ran together
     */
    @Test
    void testWorkoutTypeAndDifficulty () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();

        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, CORE);
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, EASY);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++) {
            String workoutType = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutType.indexOf(OPEN_BRACE);
            int end = workoutType.indexOf(CLOSE_BRACE);
            String workoutTypeFinal = workoutType.substring(start + 1, end);
            assertEquals(workoutTypeFinal, CORE);
            assertEquals(exerciseData.get(i).getLevel(), BEGINNER);
        }
    }

}
