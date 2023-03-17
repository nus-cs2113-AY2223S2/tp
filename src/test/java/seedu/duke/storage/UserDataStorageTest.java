package seedu.duke.storage;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeError;
import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.userdata.SessionData;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class test the reading and writing of userdata to and from the json file that will be stored on the local hard
 * disk
 */
public class UserDataStorageTest {

    private final GenerateExercise generateExercise = new GenerateExercise();

    /**
     * Tests the writing of a sample set of Completed workouts by ensuring that the current session workouts are copied
     * into an ArrayList of CompletedWorkouts in which identity codes of saved and completed workouts are the same. Both
     * sizes of the arrays must be the same.
     *
     * Finally, we check that we are able to read the same data from the json file.
     *
     * @throws DukeError Occurs when there is a read or writer error from json file
     */
    @Test
    void testSaving () throws DukeError {
        ArrayList<ExerciseData> generatedWorkouts =
                generateExercise.generateFilteredDifficultySetFrom(generateExercise.generateSetAll(), "easy");
        ArrayList<ExerciseData> sessionExercises = generateExercise.generateRandomSetFrom(generatedWorkouts, 3);
        SessionData sessionData = new SessionData(sessionExercises);
        UserCareerData userCareerData = new UserCareerData();
        userCareerData.addWorkoutSession(sessionData);
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
        WriteUserData.writeToJson(userCareerData);
        ArrayList<SessionData> completedSessionWorkouts;
        completedSessionWorkouts = LoadUserData.loadUserCareer().getTotalUserCareerSessions();
        assertEquals(completedSessionWorkouts.get(0)
                                             .getSessionExercises()
                                             .get(0)
                                             .getName(), sessionExercises.get(0).getName());
        assertEquals(3, completedSessionWorkouts
                .get(0)
                .getSessionExercises()
                .size());
    }

}
