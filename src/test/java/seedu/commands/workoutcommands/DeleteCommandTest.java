package seedu.commands.workoutcommands;

import org.junit.jupiter.api.Test;
import seedu.exceptions.InvalidArgumentException;
import seedu.parser.DateFormatter;
import seedu.workout.Day;
import seedu.workout.Workout;
import seedu.workout.WorkoutList;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class DeleteCommandTest {
    private WorkoutList workoutList;

    @Test
    public void testDeleteWorkoutCommand() throws InvalidArgumentException {
        // Create a workout list with three workouts
        WorkoutList workoutList = new WorkoutList();
        Workout workout1 = new Workout(new Date(), "Workout 1");

        Workout workout2 = new Workout(new Date(), "Workout 2");
        Workout workout3 = new Workout(new Date(), "Workout 3");
        workoutList.startWorkout(new Date());
        workoutList.addWorkout(workout2);
        workoutList.addWorkout(workout3);

        // Create the DeleteWorkoutCommand and execute it for the second workout
        int indexToDelete = 1; // delete the second workout (workout2)
        DeleteWorkoutCommand deleteCommand = new DeleteWorkoutCommand(indexToDelete);
        String result = deleteCommand.execute();

        // Check that the workout was deleted correctly
        assertEquals("Deleted Workout 2 on " + DateFormatter.dateToString(workout2.getDate()) + ".", result);
        assertEquals(2, workoutList.getWorkouts().size());
        assertEquals(workout1, workoutList.getWorkouts().get(0));
        assertEquals(workout3, workoutList.getWorkouts().get(1));

        // Try to delete a workout with an invalid index (out of bounds)
        int invalidIndex = 5; // index that is greater than the number of workouts in the list
        DeleteWorkoutCommand invalidDeleteCommand = new DeleteWorkoutCommand(invalidIndex);
        assertThrows(InvalidArgumentException.class, () -> invalidDeleteCommand.execute());
    }
}


