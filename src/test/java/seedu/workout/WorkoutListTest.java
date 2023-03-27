package seedu.workout;

import org.junit.jupiter.api.Test;
import seedu.workouttracker.workout.Workout;
import seedu.workouttracker.workout.WorkoutList;

import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutListTest {
    public SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

    //@@ author guillaume-grn
    @Test
    public void testRemoveWorkout() throws Exception {
        WorkoutList workoutList = new WorkoutList();
        String stringDate1 = "01/11/22";
        String stringDate2 = "02/11/22";
        Date date1 = dateFormat.parse(stringDate1);
        Date date2 = dateFormat.parse(stringDate2);
        Workout workout1 = new Workout(date1);
        Workout workout2 = new Workout(date2);
        workoutList.addWorkout(workout1);
        workoutList.addWorkout(workout2);

        // Check that both workouts are in the list
        assertEquals(2, workoutList.workoutList.size());

        // Remove workout1 from the list
        workoutList.removeWorkout(date1);

        // Check that workout1 was removed and workout2 is still in the list
        assertEquals(1, workoutList.workoutList.size());
        assertEquals(date2, workoutList.workoutList.get(0).getDate());

        // Try to remove workout1 again (should not be in the list)
        workoutList.removeWorkout(date1);

        // Check that workout1 was not removed and the list size is still 1
        assertEquals(1, workoutList.workoutList.size());
    }
}
