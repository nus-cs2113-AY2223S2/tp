package seedu.duke.storage;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.DukeError;
import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.userdata.Session;
import seedu.duke.userdata.UserCareerData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the writing of a sample set of Completed workouts by ensuring that the current session workouts are copied
 * into an ArrayList of CompletedWorkouts in which identity codes of saved and completed workouts are the same.
 */
public class UserDataStorageTest {
    private final String filePath = "testData.json";
    private final GenerateExercise generateExercise = new GenerateExercise();
    private final StorageHandler storageHandler = new StorageHandler(filePath);

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
        storageHandler.writeToJson(userCareerData);
        ArrayList<Session> completedSessionWorkouts;
        completedSessionWorkouts = storageHandler.loadUserCareer().getTotalUserCareerSessions();
        TestReading.testReading(completedSessionWorkouts, sessionExercises);
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
            storageHandler.writeToJson(userCareerData);
        }
        UserCareerData userCareerDataFromFile = storageHandler.loadUserCareer();
        TestReading.testReadingUserCareer(userCareerDataFromFile, userCareerData);
    }

    /**
     * Tests the handling of the case where there is a missing user data file. The program should not break but
     * generate a new empty user data file
     */
    @Test
    void testMissingUserFile () {
        File file = new File(filePath);
        boolean deletionResult = file.delete();
        assertFalse(checkIfUserFileExists(), "Testing userData file must be deleted to ensure the integrity of the " +
                "test");
        UserCareerData userCareerDataFromFile = storageHandler.loadUserCareer();
        assertNotNull(userCareerDataFromFile, "Missing instance of user career data, userCareerData must be empty but" +
                " initialised");
        assertTrue(checkIfUserFileExists(), "New user file has not been created");
    }

    /**
     * Tests the initialisation of localdatetime in Sessions by generating a sample test session and comparing the
     * current pc time against the datetime that was initialised in the newly created Session. The user data is saved
     * for testing of the deserializer later.
     *
     * @throws DukeError Occurs when there is a file writer error.
     */
    @Test
    void testDateTime () throws DukeError {
        ArrayList<ExerciseData> generatedWorkouts = generateExercise.generateFilteredDifficultySetFrom(
                generateExercise.generateSetAll(), "hard");
        ArrayList<ExerciseData> sessionExercises = generateExercise.generateRandomSetFrom(generatedWorkouts, 5);
        Session session = new Session(sessionExercises);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String timeSession = dateTimeFormatter.format(session.getDateAdded());
        String currentTime = dateTimeFormatter.format(LocalDateTime.now());
        assertEquals(timeSession, currentTime, "Session datetime conflict!");
        UserCareerData userCareerData = new UserCareerData();
        userCareerData.addWorkoutSession(session);
    }

    /**
     * Tests the custom LocalDateTime Serializer and Deserializer by comparing the time from the user file and time
     * on the pc. Time offset should not be longer than one minute.
     */
    @Test
    void testDateTimeSerializers () {
        UserCareerData userCareerDataFromFile = storageHandler.loadUserCareer();
        Session session = userCareerDataFromFile.getTotalUserCareerSessions().get(0);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String timeFromFile = dateTimeFormatter.format(session.getDateAdded());
        String currentTime = dateTimeFormatter.format(LocalDateTime.now());
        assertEquals(timeFromFile, currentTime, "User file and current time conflict, conflict should not be more " +
                "than 1 minute");
        deleteTestingFile();
    }

    /**
     * Checks if user file exists to assert the presence of the user file
     *
     * @return Presence of the user file in program's root directory
     */
    private boolean checkIfUserFileExists () {
        File userFile = new File(filePath);
        return userFile.exists();
    }

    private void deleteTestingFile () {
        File file = new File(filePath);
        boolean deletionResult = file.delete();
        assertTrue(deletionResult, "Unable to delete testing user data file, Ensure all other programs are not using " +
                "the file");
    }

}
