package seedu.duke.storage;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeError;
import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.userdata.TodoWorkout;

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
     * <p>
     * Finally, we check that we are able to read the same data from the json file.
     *
     * @throws DukeError Occurs when there is a read or writer error from json file
     */
    @Test
    void testSaving() throws DukeError {
        ArrayList<ExerciseData> sessionWorkouts =
                generateExercise.generateFilteredDifficultySetFrom(generateExercise.generateSetAll(), "easy");
        sessionWorkouts = generateExercise.generateRandomSetFrom(sessionWorkouts, 3);
        ArrayList<TodoWorkout> completedSessionWorkouts = new ArrayList<>();
        for (ExerciseData exercise : sessionWorkouts) {
            completedSessionWorkouts.add(new TodoWorkout(exercise));
            assertEquals(System.identityHashCode(exercise),
                    System.identityHashCode(completedSessionWorkouts
                            .get(completedSessionWorkouts.size() - 1)
                            .getExerciseData()));
        }
        assertEquals(3, completedSessionWorkouts.size());
        WriteUserData.writeToJson(completedSessionWorkouts);
        ArrayList<TodoWorkout> completedWorkouts;
        completedWorkouts = LoadUserData.loadCompletedWorkouts();
        assertEquals(completedWorkouts.get(0).getExerciseData().getName(), sessionWorkouts.get(0).getName());
        assertEquals(3, completedWorkouts.size());
    }

}
