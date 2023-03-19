package seedu.duke.storage;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeError;
import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.userdata.Session;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the writing of a sample set of Completed workouts by ensuring that the current session workouts are copied
 * into an ArrayList of CompletedWorkouts in which identity codes of saved and completed workouts are the same.
 */
public class UserDataStorageTest {
    private final String FILEPATH = "testData.json";
    private final GenerateExercise generateExercise = new GenerateExercise();
    private StorageHandler storageHandler = new StorageHandler(FILEPATH);

    /**
     * Initialises the data by generating the workouts for this pseudo-session and adds to a new session and then to
     * the user career.
     * Writing of data is checked and then written to the json file
     * Data from the saved jsonfile is now read into completedSessionWorkouts and then checked if exercises added are
     * correct
     * User data file is deleted for testing purposes
     *
     * @throws DukeError Occurs when there is a file read/write error
     */
    @Test
    void testSaving () throws DukeError {
        ArrayList<ExerciseData> generatedWorkouts = generateExercise.generateFilteredDifficultySetFrom(
                generateExercise.generateSetAll(), "easy");
        ArrayList<ExerciseData> sessionExercises = generateExercise.generateRandomSetFrom(generatedWorkouts, 3);
        Session session = new Session(sessionExercises);
        UserCareerData userCareerData = new UserCareerData();
        userCareerData.addWorkoutSession(session);
        TestWriting.testWriting(sessionExercises, userCareerData);
        UserDataWriter.writeToJson("testData.json", userCareerData);
        ArrayList<Session> completedSessionWorkouts;
        completedSessionWorkouts = UserDataLoader.loadUserCareer("testData.json").getTotalUserCareerSessions();
        TestReading.testReading(completedSessionWorkouts, sessionExercises);
        File file = new File("testData.json");
        boolean deletionResult = file.delete();
        assertTrue(deletionResult, "Unable to delete testing user data file, Ensure all other programs are not using " +
                "the file");
    }

    /**
     * Tests the writing and reading of multiple user sessions of varying exercises within each session
     * User data is written to the file and checked upon data from the file and original data
     *
     * @throws DukeError
     */
    @Test
    void testReadingCareerData () throws DukeError {
        ArrayList<ExerciseData> generatedWorkouts = generateExercise.generateFilteredDifficultySetFrom(
                generateExercise.generateSetAll(), "medium");
        UserCareerData userCareerData = new UserCareerData();
        Random random = new Random();
        random.setSeed(1);
        int sessions = random.nextInt(30);
        for (int i = 0; i < sessions; i++) {
            ArrayList<ExerciseData> sessionWorkouts = generateExercise.generateRandomSetFrom(generatedWorkouts,
                                                                                             random.nextInt(30));
            Session session = new Session(sessionWorkouts);
            userCareerData.addWorkoutSession(session);
            UserDataWriter.writeToJson("testData.json", userCareerData);
        }
        UserCareerData userCareerDataFromFile = UserDataLoader.loadUserCareer("testData.json");
        TestReading.testReadingUserCareer(userCareerDataFromFile, userCareerData);
        File file = new File("testData.json");
        boolean deletionResult = file.delete();
        assertTrue(deletionResult, "Unable to delete testing user data file, Ensure all other programs are not using " +
                "the file");

    }

}
