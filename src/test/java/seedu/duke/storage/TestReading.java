package seedu.duke.storage;

import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;
import seedu.duke.data.userdata.Session;
import seedu.duke.data.userdata.UserCareerData;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author EangJS
public class TestReading {
    /**
     * Tests the reading of user career data from the json file such that all data from the json file is correctly
     * read into the respective classes.
     *
     * @param completedSessionWorkouts The arrayList of all completed sessions from the userCareerData (in this
     *     test case only one session is created) that has been read from the json file.
     * @param sessionExercises The total completed session exercises that was initially added before saving to
     *     the json file which is used as the reference for this test.
     */
    public static void testReading (ArrayList<Session> completedSessionWorkouts,
                                    ArrayList<ExerciseData> sessionExercises) {
        assertEquals(completedSessionWorkouts.get(0)
                                             .getSessionExercises()
                                             .get(0)
                                             .getName(), sessionExercises.get(0).getName());
        assertEquals(3, completedSessionWorkouts
            .get(0)
            .getSessionExercises()
            .size());
    }

    public static void testReadingUserCareer (UserCareerData userCareerDataFromFile,
                                              UserCareerData userCareerDataFromUser) {
        ArrayList<Session> userCareerSessionsFromFile = userCareerDataFromFile.getTotalUserCareerSessions();
        ArrayList<Session> userCareerSessionsFromUser = userCareerDataFromUser.getTotalUserCareerSessions();
        assertEquals(userCareerSessionsFromFile.size(), userCareerSessionsFromUser.size(), "Different session counts " +
            "from user and file");
        for (int i = 0; i < userCareerSessionsFromUser.size(); i++) {
            ArrayList<ExerciseData> sessionExercisesFromUser = userCareerSessionsFromUser.get(i).getSessionExercises();
            ArrayList<ExerciseData> sessionExercisesFromFile = userCareerSessionsFromFile.get(i).getSessionExercises();
            assertEquals(sessionExercisesFromFile.size(), sessionExercisesFromUser.size(), "Different exercise data " +
                "sizes from user and file");
            for (int j = 0; j < sessionExercisesFromUser.size(); j++) {
                ExerciseData exerciseFromUser = sessionExercisesFromUser.get(j);
                ExerciseData exerciseFromFile = sessionExercisesFromFile.get(j);
                assertEquals(exerciseFromFile.getName(), exerciseFromUser.getName(), "Different exercises in each " +
                    "sesssion");
            }

        }

    }

}
