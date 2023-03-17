package seedu.duke.storage;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeError;
import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.userdata.Session;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the writing of a sample set of Completed workouts by ensuring that the current session workouts are copied
 * into an ArrayList of CompletedWorkouts in which identity codes of saved and completed workouts are the same.
 */
public class UserDataStorageTest {

    private final GenerateExercise generateExercise = new GenerateExercise();

    /**
     * Tests the mapping of data into the UserCareerData class such that all sessionExercises are mapped accordingly.
     * This test checks that all sessionExercises are mapped correctly to the userCareer by checking the identity has
     * code of each exercise that is written into the sessionExercise.
     *
     * @param sessionExercises exercises that has been completed by the user in the current session but not yet
     *         added to the user career data.
     * @param userCareerData All exercises that the user has completed, but for this test only a single session
     *         is used.
     */
    void testWriting (ArrayList<ExerciseData> sessionExercises, UserCareerData userCareerData) {
        int i = 0;
        for (ExerciseData sessionExercise : sessionExercises) {
            assertEquals(System.identityHashCode(sessionExercise)
                    , System.identityHashCode(userCareerData
                                                      .getTotalUserCareerSessions()
                                                      .get(0)
                                                      .getSessionExercises()
                                                      .get(i)));
            i++;
        }
        assertEquals(3, userCareerData.getTotalUserCareerSessions()
                                      .get(0)
                                      .getSessionExercises()
                                      .size());

    }

    /**
     * Tests the reading of user career data from the json file such that all data from the json file is correctly
     * read into the respective classes.
     *
     * @param completedSessionWorkouts The arrayList of all completed sessions from the userCareerData (in this
     *         test case only one session is created) that has been read from the json file.
     * @param sessionExercises The total completed session exercises that was initially added before saving to
     *         the json file which is used as the reference for this test.
     */
    void testReading (ArrayList<Session> completedSessionWorkouts, ArrayList<ExerciseData> sessionExercises) {
        assertEquals(completedSessionWorkouts.get(0)
                                             .getSessionExercises()
                                             .get(0)
                                             .getName(), sessionExercises.get(0).getName());
        assertEquals(3, completedSessionWorkouts
                .get(0)
                .getSessionExercises()
                .size());
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
    void testSaving () throws DukeError {
        ArrayList<ExerciseData> generatedWorkouts = generateExercise.generateFilteredDifficultySetFrom(
                generateExercise.generateSetAll(), "easy");
        ArrayList<ExerciseData> sessionExercises = generateExercise.generateRandomSetFrom(generatedWorkouts, 3);
        Session session = new Session(sessionExercises);
        UserCareerData userCareerData = new UserCareerData();
        userCareerData.addWorkoutSession(session);
        testWriting(sessionExercises, userCareerData);
        WriteUserData.writeToJson("testData.json", userCareerData);
        ArrayList<Session> completedSessionWorkouts;
        completedSessionWorkouts = LoadUserData.loadUserCareer("testData.json").getTotalUserCareerSessions();
        testReading(completedSessionWorkouts, sessionExercises);
    }

}
