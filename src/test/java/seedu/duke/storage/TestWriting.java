package seedu.duke.storage;

import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;
import seedu.duke.data.userdata.UserCareerData;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author EangJS
public class TestWriting {
    /**
     * Tests the mapping of data into the UserCareerData class such that all sessionExercises are mapped accordingly.
     * This test checks that all sessionExercises are mapped correctly to the userCareer by checking the identity has
     * code of each exercise that is written into the sessionExercise.
     *
     * @param sessionExercises exercises that has been completed by the user in the current session but not yet
     *     added to the user career data.
     * @param userCareerData All exercises that the user has completed, but for this test only a single session
     *     is used.
     */
    public static void testWriting (ArrayList<ExerciseData> sessionExercises, UserCareerData userCareerData) {
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

}
