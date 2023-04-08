package seedu.duke.data.userdata;

import org.junit.jupiter.api.Test;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserCareerDataTest {
    /**
     * Checks if the program prints all finished sessions correctly.
     *
     */

    @Test
    void testPrintAllFinishedSessions() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));
        UserCareerData userCareerData = new UserCareerData();
        userCareerData.printAllFinishedWorkoutSessions();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "You have not completed any exercises :(\r\n" +
                    "Add on to this list by completing a workout session!\r\n";
        } else {
            expectedOutput = "You have not completed any exercises :(\n" +
                    "Add on to this list by completing a workout session!\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    /**
     * Test to check if a workout session is added correctly.
     */

    @Test
    void testAddSession() {
        ArrayList<ExerciseData> exerciseList = new ArrayList<>();
        GenerateExercise generateExercise = new GenerateExercise();
        ArrayList<ExerciseData> exerciseData = generateExercise.generateSetAll();
        exerciseList.add(exerciseData.get(773));
        UserCareerData userCareerData = new UserCareerData();
        Session session = new Session(exerciseList);
        userCareerData.addWorkoutSession(session);
        assertEquals(userCareerData.getTotalUserCareerSessions().size(), 1);
    }

    /**
     * Test to check if a workout session is deleted correctly.
     */

    @Test
    void testDeleteSession() {
        ArrayList<ExerciseData> exerciseList = new ArrayList<>();
        GenerateExercise generateExercise = new GenerateExercise();
        ArrayList<ExerciseData> exerciseData = generateExercise.generateSetAll();
        exerciseList.add(exerciseData.get(773));
        UserCareerData userCareerData = new UserCareerData();
        Session session = new Session(exerciseList);
        userCareerData.addWorkoutSession(session);
        userCareerData.deleteWorkoutSession(1);
        assertEquals(userCareerData.getTotalUserCareerSessions().size(), 0);
    }
}