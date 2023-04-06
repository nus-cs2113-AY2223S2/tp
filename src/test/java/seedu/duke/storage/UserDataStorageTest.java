package seedu.duke.storage;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;
import seedu.duke.data.userdata.Session;
import seedu.duke.data.userdata.UserCareerData;
import seedu.duke.data.userdata.userplan.Plan;
import seedu.duke.data.userdata.userplan.UserPlan;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author EangJS

/**
 * Tests the writing of a sample set of Completed workouts by ensuring that the current session workouts are copied
 * into an ArrayList of CompletedWorkouts in which identity codes of saved and completed workouts are the same.
 */
public class UserDataStorageTest {
    private static final String filePath = "testData.json";
    private static final String plansPath = "testPlans.json";
    private final GenerateExercise generateExercise = new GenerateExercise();
    private final UserPlansStorage userPlansStorage = new JsonUserPlansStorage(plansPath);
    private final UserCareerStorage userCareerStorage = new JsonUserCareerStorage(filePath);
    private final Storage storage = new StorageManager(userCareerStorage, userPlansStorage);

    @AfterAll
    @BeforeAll
    public static void deleteTestingFile () {
        File userDatafile = new File(filePath);
        File plansFile = new File(plansPath);
        plansFile.delete();
        userDatafile.delete();
    }

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
    void testBasicJsonIO () throws DukeError {
        ArrayList<ExerciseData> generatedWorkouts = generateExercise.generateFilteredDifficultySetFrom(
            generateExercise.generateSetAll(), "easy");
        ArrayList<ExerciseData> sessionExercises = generateExercise.generateRandomSetFrom(generatedWorkouts, 3);
        Session session = new Session(sessionExercises);
        UserCareerData userCareerData = new UserCareerData();
        userCareerData.addWorkoutSession(session);
        TestWriting.testWriting(sessionExercises, userCareerData);
        storage.writeToJson(userCareerData);
        assertDoesNotThrow(() -> new DukeError("File Write Error"));
        ArrayList<Session> completedSessionWorkouts;
        completedSessionWorkouts = storage.loadUserData().getTotalUserCareerSessions();
        TestReading.testReading(completedSessionWorkouts, sessionExercises);
    }

    /**
     * Test Plans
     */
    @Test
    void testSavingPlans () throws DukeError {
        UserPlan userPlan = new UserPlan();
        ArrayList<String> samplePlan = new ArrayList<>();
        samplePlan.add("Upper");
        samplePlan.add("easy");
        Plan plan = new Plan(samplePlan, "Sample Plan Name");
        for (int i = 0; i < 7; i++) {
            userPlan.addDayPlan(plan, i);
        }
        storage.writeToJson(userPlan);
        assertDoesNotThrow(() -> new DukeError("File Write Error"));
    }

    /**
     * Tests the writing and reading of multiple user sessions of varying exercises within each session
     * User data is written to the file and checked upon data from the file and original data
     *
     * @throws DukeError occurs when there is an error in reading from the file
     */
    @Test
    void testIntermediateJsonIO () throws DukeError {
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
            storage.writeToJson(userCareerData);
        }
        UserCareerData userCareerDataFromFile = storage.loadUserData();
        TestReading.testReadingUserCareer(userCareerDataFromFile, userCareerData);
    }

    /**
     * Test Reading Plans
     */
    @Test
    void testReadingPlans () {
        UserPlan userPlan = new UserPlan();
        ArrayList<String> samplePlan = new ArrayList<>();
        samplePlan.add("Upper");
        samplePlan.add("easy");
        Plan plan = new Plan(samplePlan, "Sample Plan Name");
        userPlan = storage.loadUserPlans();
        for (int i = 0; i < 7; i++) {
            Plan todayPlans = userPlan.getDayPlans(i).get(0);
            assertEquals(todayPlans.getPlanName(), plan.getPlanName());
            ArrayList<String> exercisePlans = todayPlans.getExercisePlans();
            assertEquals(exercisePlans.get(0), samplePlan.get(0));
            assertEquals(exercisePlans.get(1), samplePlan.get(1));
            assertEquals(exercisePlans.size(), samplePlan.size());
        }
    }

    /**
     * Tests the handling of the case where there is a missing user data file. The program should not break but
     * generate a new empty user data file
     */
    @Test
    void testMissingUserFile () {
        File file = new File(filePath);
        file.delete();
        assertFalse(checkIfUserFileExists(filePath),
                    "Testing userData file must be deleted to ensure the integrity of the " +
                        "test");
        UserCareerData userCareerDataFromFile = storage.loadUserData();
        assertNotNull(userCareerDataFromFile, "Missing instance of user career data, userCareerData must be empty but" +
            " initialised");
        assertTrue(checkIfUserFileExists(filePath), "New user file has not been created");
    }

    /**
     * Tests the handling of the case where there is a missing user data file. The program should not break but
     * generate a new empty user data file
     */
    @Test
    void testMissingPlansFile () {
        File file = new File(plansPath);
        file.delete();
        assertFalse(checkIfUserFileExists(plansPath),
                    "Testing planning file must be deleted to ensure the integrity of the " +
                        "test");
        UserPlan userPlan = storage.loadUserPlans();
        assertNotNull(userPlan, "Missing instance of user plans data, user plans must be empty but" +
            " initialised");
        assertTrue(checkIfUserFileExists(filePath), "New plans file has not been created");
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
        UserCareerData userCareerDataFromFile = storage.loadUserData();
        Session session = userCareerDataFromFile.getTotalUserCareerSessions().get(0);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String timeFromFile = dateTimeFormatter.format(session.getDateAdded());
        String currentTime = dateTimeFormatter.format(LocalDateTime.now());
        assertEquals(timeFromFile, currentTime, "User file and current time conflict, conflict should not be more " +
            "than 1 minute");
    }

    /**
     * Method to check if user file exists to assert the presence of the user file
     *
     * @param filePath user defined file path
     * @return Presence of the user file in program's root directory
     */
    private boolean checkIfUserFileExists (String filePath) {
        File userFile = new File(filePath);
        return userFile.exists();
    }

}
