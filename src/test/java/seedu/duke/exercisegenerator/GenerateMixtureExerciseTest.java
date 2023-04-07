package seedu.duke.exercisegenerator;

import org.junit.jupiter.api.Test;
import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//@@author ChubbsBunns
/** This class function is made to ensure that all generate permutations work, since each category fetches data from
 * the json data file slightly differently.
 * This ensures as much *PATH COVERAGE* as possible for *TEST COVERAGE*
 */
public class GenerateMixtureExerciseTest {

    private static final String EASY = "easy";
    private static final String MEDIUM = "medium";
    private static final String HARD = "hard";
    private static final String UPPER = "upper";
    private static final String UPPER_BODY = "upper body";
    private static final String CORE = "core";
    private static final String LEGS = "legs";
    private static final String NULL = "null";
    private static final String OUTPUT_BODY = "body only";

    private static final String BEGINNER = "beginner";
    private static final String INTERMEDIATE = "intermediate";
    private static final String EXPERT = "expert";
    private static final String OPEN_BRACE = "[";
    private static final String CLOSE_BRACE = "]";

    /**
     * For the following test cases, each test function name will be prefixed with the following
     * letters:
     * U: upper
     * C: core
     * L: legs
     * F: easy
     * M: medium
     * H: hard
     * S: static
     * G: gym
     * Each combination will be the test for that particular test case
     * E.g. testUE will test a combination of the "upper" parameter and the "easy" parameter
     */

    @Test
    void testUES () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, UPPER);
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, EASY);
            exerciseData = generateExercise.generateFilteredStaticSetFrom(exerciseData);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++) {
            String workoutTypeRaw = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutTypeRaw.indexOf(OPEN_BRACE);
            int end = workoutTypeRaw.indexOf(CLOSE_BRACE);
            String workoutType = workoutTypeRaw.substring(start + 1, end);
            //For upper body
            assertEquals(workoutType, UPPER_BODY);
            //For easy
            assertEquals(exerciseData.get(i).getLevel(), BEGINNER);
            //For static exercises
            assertNotEquals(NULL, exerciseData.get(i).getEquipment());
            assertEquals(OUTPUT_BODY, exerciseData.get(i).getEquipment());
        }
    }

    @Test
    void testUMS () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, UPPER);
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, MEDIUM);
            exerciseData = generateExercise.generateFilteredStaticSetFrom(exerciseData);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++) {
            String workoutTypeRaw = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutTypeRaw.indexOf(OPEN_BRACE);
            int end = workoutTypeRaw.indexOf(CLOSE_BRACE);
            String workoutType = workoutTypeRaw.substring(start + 1, end);
            //For upper body
            assertEquals(workoutType, UPPER_BODY);
            assertEquals(exerciseData.get(i).getLevel(), INTERMEDIATE);
            //For static exercises
            assertNotEquals(NULL, exerciseData.get(i).getEquipment());
            assertEquals(OUTPUT_BODY, exerciseData.get(i).getEquipment());
        }
    }

    @Test
    void testUHS () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, UPPER);
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, HARD);
            exerciseData = generateExercise.generateFilteredStaticSetFrom(exerciseData);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++) {
            String workoutTypeRaw = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutTypeRaw.indexOf(OPEN_BRACE);
            int end = workoutTypeRaw.indexOf(CLOSE_BRACE);
            String workoutType = workoutTypeRaw.substring(start + 1, end);
            //For upper body
            assertEquals(workoutType, UPPER_BODY);
            assertEquals(exerciseData.get(i).getLevel(), EXPERT);
            //For static exercises
            assertNotEquals(NULL, exerciseData.get(i).getEquipment());
            assertEquals(OUTPUT_BODY, exerciseData.get(i).getEquipment());
        }
    }

    @Test
    void testUEG () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, UPPER);
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, EASY);
            exerciseData = generateExercise.generateFilteredGymSetFrom(exerciseData);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++) {
            String workoutTypeRaw = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutTypeRaw.indexOf(OPEN_BRACE);
            int end = workoutTypeRaw.indexOf(CLOSE_BRACE);
            String workoutType = workoutTypeRaw.substring(start + 1, end);
            //For upper body
            assertEquals(workoutType, UPPER_BODY);
            //For easy
            assertEquals(exerciseData.get(i).getLevel(), BEGINNER);
            //For body exercises
            assertNotEquals(exerciseData.get(i).getEquipment(), null);
            assertNotEquals(exerciseData.get(i).getEquipment(), OUTPUT_BODY);
        }
    }

    @Test
    void testUMG () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, UPPER);
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, MEDIUM);
            exerciseData = generateExercise.generateFilteredGymSetFrom(exerciseData);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (ExerciseData exerciseDatum : exerciseData) {
            String workoutTypeRaw = exerciseDatum.getWorkoutType().toString();
            int start = workoutTypeRaw.indexOf(OPEN_BRACE);
            int end = workoutTypeRaw.indexOf(CLOSE_BRACE);
            String workoutType = workoutTypeRaw.substring(start + 1, end);
            //For upper body
            assertEquals(workoutType, UPPER_BODY);
            assertEquals(exerciseDatum.getLevel(), INTERMEDIATE);
            //For body exercises
            assertNotEquals(exerciseDatum.getEquipment(), null);
            assertNotEquals(exerciseDatum.getEquipment(), OUTPUT_BODY);
        }
    }

    @Test
    void testUHG () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, UPPER);
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, HARD);
            exerciseData = generateExercise.generateFilteredGymSetFrom(exerciseData);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (ExerciseData exerciseDatum : exerciseData) {
            String workoutTypeRaw = exerciseDatum.getWorkoutType().toString();
            int start = workoutTypeRaw.indexOf(OPEN_BRACE);
            int end = workoutTypeRaw.indexOf(CLOSE_BRACE);
            String workoutType = workoutTypeRaw.substring(start + 1, end);
            assertEquals(workoutType, UPPER_BODY);
            assertEquals(exerciseDatum.getLevel(), EXPERT);
            assertNotEquals(exerciseDatum.getEquipment(), null);
            assertNotEquals(exerciseDatum.getEquipment(), OUTPUT_BODY);
        }
    }

    @Test
    void testCEG () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, CORE);
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, EASY);
            exerciseData = generateExercise.generateFilteredGymSetFrom(exerciseData);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++) {
            String workoutTypeRaw = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutTypeRaw.indexOf(OPEN_BRACE);
            int end = workoutTypeRaw.indexOf(CLOSE_BRACE);
            String workoutType = workoutTypeRaw.substring(start + 1, end);
            assertEquals(workoutType, CORE);
            assertEquals(exerciseData.get(i).getLevel(), BEGINNER);
            assertNotEquals(exerciseData.get(i).getEquipment(), null);
            assertNotEquals(exerciseData.get(i).getEquipment(), OUTPUT_BODY);
        }
    }

    @Test
    void testCMG () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, CORE);
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, MEDIUM);
            exerciseData = generateExercise.generateFilteredGymSetFrom(exerciseData);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++) {
            String workoutTypeRaw = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutTypeRaw.indexOf(OPEN_BRACE);
            int end = workoutTypeRaw.indexOf(CLOSE_BRACE);
            String workoutType = workoutTypeRaw.substring(start + 1, end);
            assertEquals(workoutType, CORE);
            assertEquals(exerciseData.get(i).getLevel(), INTERMEDIATE);
            assertNotEquals(exerciseData.get(i).getEquipment(), null);
            assertNotEquals(exerciseData.get(i).getEquipment(), OUTPUT_BODY);
        }
    }

    @Test
    void testCHG () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, CORE);
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, HARD);
            exerciseData = generateExercise.generateFilteredGymSetFrom(exerciseData);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++) {
            String workoutTypeRaw = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutTypeRaw.indexOf(OPEN_BRACE);
            int end = workoutTypeRaw.indexOf(CLOSE_BRACE);
            String workoutType = workoutTypeRaw.substring(start + 1, end);
            assertEquals(workoutType, CORE);
            assertEquals(exerciseData.get(i).getLevel(), EXPERT);
            assertNotEquals(exerciseData.get(i).getEquipment(), null);
            assertNotEquals(exerciseData.get(i).getEquipment(), OUTPUT_BODY);
        }
    }

    @Test
    void testCES () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, CORE);
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, EASY);
            exerciseData = generateExercise.generateFilteredStaticSetFrom(exerciseData);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++) {
            String workoutTypeRaw = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutTypeRaw.indexOf(OPEN_BRACE);
            int end = workoutTypeRaw.indexOf(CLOSE_BRACE);
            String workoutType = workoutTypeRaw.substring(start + 1, end);
            assertEquals(workoutType, CORE);
            assertEquals(exerciseData.get(i).getLevel(), BEGINNER);
            assertNotEquals(NULL, exerciseData.get(i).getEquipment());
            assertEquals(OUTPUT_BODY, exerciseData.get(i).getEquipment());
        }
    }

    @Test
    void testCMS () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, CORE);
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, MEDIUM);
            exerciseData = generateExercise.generateFilteredStaticSetFrom(exerciseData);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++) {
            String workoutTypeRaw = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutTypeRaw.indexOf(OPEN_BRACE);
            int end = workoutTypeRaw.indexOf(CLOSE_BRACE);
            String workoutType = workoutTypeRaw.substring(start + 1, end);
            assertEquals(workoutType, CORE);
            assertEquals(exerciseData.get(i).getLevel(), INTERMEDIATE);
            assertNotEquals(NULL, exerciseData.get(i).getEquipment());
            assertEquals(OUTPUT_BODY, exerciseData.get(i).getEquipment());
        }
    }

    @Test
    void testCHS () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, CORE);
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, HARD);
            exerciseData = generateExercise.generateFilteredStaticSetFrom(exerciseData);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++) {
            String workoutTypeRaw = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutTypeRaw.indexOf(OPEN_BRACE);
            int end = workoutTypeRaw.indexOf(CLOSE_BRACE);
            String workoutType = workoutTypeRaw.substring(start + 1, end);
            assertEquals(workoutType, CORE);
            assertEquals(exerciseData.get(i).getLevel(), EXPERT);
            assertNotEquals(NULL, exerciseData.get(i).getEquipment());
            assertEquals(OUTPUT_BODY, exerciseData.get(i).getEquipment());
        }
    }

    @Test
    void testLEG () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, LEGS);
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, EASY);
            exerciseData = generateExercise.generateFilteredGymSetFrom(exerciseData);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++) {
            String workoutTypeRaw = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutTypeRaw.indexOf(OPEN_BRACE);
            int end = workoutTypeRaw.indexOf(CLOSE_BRACE);
            String workoutType = workoutTypeRaw.substring(start + 1, end);
            assertEquals(workoutType, LEGS);
            assertEquals(exerciseData.get(i).getLevel(), BEGINNER);
            assertNotEquals(exerciseData.get(i).getEquipment(), null);
            assertNotEquals(exerciseData.get(i).getEquipment(), OUTPUT_BODY);
        }
    }

    @Test
    void testLMG () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, LEGS);
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, MEDIUM);
            exerciseData = generateExercise.generateFilteredGymSetFrom(exerciseData);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++) {
            String workoutTypeRaw = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutTypeRaw.indexOf(OPEN_BRACE);
            int end = workoutTypeRaw.indexOf(CLOSE_BRACE);
            String workoutType = workoutTypeRaw.substring(start + 1, end);
            assertEquals(workoutType, LEGS);
            assertEquals(exerciseData.get(i).getLevel(), INTERMEDIATE);
            assertNotEquals(exerciseData.get(i).getEquipment(), null);
            assertNotEquals(exerciseData.get(i).getEquipment(), OUTPUT_BODY);
        }
    }

    @Test
    void testLHG () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, LEGS);
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, HARD);
            exerciseData = generateExercise.generateFilteredGymSetFrom(exerciseData);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++) {
            String workoutTypeRaw = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutTypeRaw.indexOf(OPEN_BRACE);
            int end = workoutTypeRaw.indexOf(CLOSE_BRACE);
            String workoutType = workoutTypeRaw.substring(start + 1, end);
            assertEquals(workoutType, LEGS);
            assertEquals(exerciseData.get(i).getLevel(), EXPERT);
            assertNotEquals(exerciseData.get(i).getEquipment(), null);
            assertNotEquals(exerciseData.get(i).getEquipment(), OUTPUT_BODY);
        }
    }

    @Test
    void testLES () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, LEGS);
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, EASY);
            exerciseData = generateExercise.generateFilteredStaticSetFrom(exerciseData);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++) {
            String workoutTypeRaw = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutTypeRaw.indexOf(OPEN_BRACE);
            int end = workoutTypeRaw.indexOf(CLOSE_BRACE);
            String workoutType = workoutTypeRaw.substring(start + 1, end);
            assertEquals(workoutType, LEGS);
            assertEquals(exerciseData.get(i).getLevel(), BEGINNER);
            assertNotEquals(NULL, exerciseData.get(i).getEquipment());
            assertEquals(OUTPUT_BODY, exerciseData.get(i).getEquipment());
        }
    }

    @Test
    void testLMS () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, LEGS);
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, MEDIUM);
            exerciseData = generateExercise.generateFilteredStaticSetFrom(exerciseData);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++) {
            String workoutTypeRaw = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutTypeRaw.indexOf(OPEN_BRACE);
            int end = workoutTypeRaw.indexOf(CLOSE_BRACE);
            String workoutType = workoutTypeRaw.substring(start + 1, end);
            assertEquals(workoutType, LEGS);
            assertEquals(exerciseData.get(i).getLevel(), INTERMEDIATE);
            assertNotEquals(NULL, exerciseData.get(i).getEquipment());
            assertEquals(OUTPUT_BODY, exerciseData.get(i).getEquipment());
        }
    }

    @Test
    void testLHS () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, LEGS);
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, HARD);
            exerciseData = generateExercise.generateFilteredStaticSetFrom(exerciseData);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++) {
            String workoutTypeRaw = exerciseData.get(i).getWorkoutType().toString();
            int start = workoutTypeRaw.indexOf(OPEN_BRACE);
            int end = workoutTypeRaw.indexOf(CLOSE_BRACE);
            String workoutType = workoutTypeRaw.substring(start + 1, end);
            assertEquals(workoutType, LEGS);
            assertEquals(exerciseData.get(i).getLevel(), EXPERT);
            assertNotEquals(NULL, exerciseData.get(i).getEquipment());
            assertEquals(OUTPUT_BODY, exerciseData.get(i).getEquipment());
        }
    }


    @Test
    void testMixture () {
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, "medium");
            exerciseData = generateExercise.generateFilteredStaticSetFrom(exerciseData);
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < exerciseData.size(); i++) {
            assertEquals(exerciseData.get(i).getLevel(), "intermediate");
            assertNotEquals(exerciseData.get(i).getEquipment(), null);
            assertEquals(exerciseData.get(i).getEquipment(), "body only");
        }
    }



}
