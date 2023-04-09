/*
package seedu.workout;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class WorkoutListTest {

    private WorkoutList workoutList;
    private HashMap<> workouts;
    exercises = new ArrayList<>()

    //@@ author ZIZI-czh
    @BeforeEach
    public void setUp() {
        workoutList = new WorkoutList();
        workouts = new HashMap<>();
    }

    //@@ author ZIZI-czh
    @Test
    public void testAddWorkoutToList() throws InvalidArgumentException {
        Date date = new Date();
        workoutList.startWorkout(date, "workout1");
        workouts.put(date, "rtt");
        assertEquals(workouts, workoutList.getWorkouts());
    }

    //@@ author ZIZI-czh
    @Test
    public void testGetWorkoutsInSpecificWeek() throws InvalidArgumentException {
        Date date = new Date();
        workoutList.startWorkout(date, "workout1");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date startOfWeekDate = calendar.getTime();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date endOfWeekDate = calendar.getTime();
        ArrayList<Workout> workoutsInSpecificWeek = workoutList.getWorkoutsInSpecificWeek(startOfWeekDate);
        assertTrue(workoutsInSpecificWeek.contains(date));
        assertFalse(workoutsInSpecificWeek.contains(endOfWeekDate));
    }

}

*/

package seedu.workout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutTest {
    private Workout workout;

    @BeforeEach
    public void setUp() {
        workout = new Workout(LocalDateTime.now(), "testWorkout");
    }

    @Test
    public void startWorkout_shouldStartWorkoutAndReturnTrue() {
        boolean result = workout.startWorkout();
        assertEquals(true, result);
    }

    @Test
    public void startWorkout_shouldNotStartWorkoutWhenAlreadyStarted() {
        workout.startWorkout();
        boolean result = workout.startWorkout();
        assertEquals(false, result);
    }
}
