package seedu.commands.workoutcommands;

import org.junit.jupiter.api.Test;
import seedu.exceptions.InvalidArgumentException;
import seedu.workout.Workout;
import seedu.workout.WorkoutList;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


//@@author ZIZI-czh
public class StartWorkoutCommandTest {
    private static final String DEFAULT_WORKOUT_NAME = "Test workout";
    private WorkoutList workoutList;

    @Test
    public void startWorkout_validWorkout_success() {
        // Set up
        workoutList = new WorkoutList();
        Date today = new Date();
        String workoutName = DEFAULT_WORKOUT_NAME;

        // Exercise
        try {
            workoutList.startWorkout(today, workoutName);
        } catch (InvalidArgumentException e) {
            // Should not reach here
            assertEquals(1, 0);
        }

        // Assert
        Workout createdWorkout = workoutList.getWorkouts().get(0);
        assertEquals(today, createdWorkout.getDate());
        assertEquals(workoutName, createdWorkout.getWorkoutName());
    }

    @Test
    public void startWorkout_workoutAlreadyExists_exceptionThrown() {
        // Set up
        workoutList = new WorkoutList();
        Date today = new Date();
        String workoutName = DEFAULT_WORKOUT_NAME;
        try {
            workoutList.startWorkout(today, workoutName);
        } catch (InvalidArgumentException e) {
            // Should not reach here
            assertEquals(1, 0);
        }

        // Exercise and assert
        assertThrows(InvalidArgumentException.class, () -> workoutList.startWorkout(today, workoutName));
    }

}

