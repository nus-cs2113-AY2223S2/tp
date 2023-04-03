package seedu.workout;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class WorkoutListTest {

    private WorkoutList workoutList;
    private HashMap<Date, Day> workouts;

    //@@ author ZIZI-czh
    @BeforeEach
    public void setUp() {
        workoutList = new WorkoutList();
        workouts = new HashMap<>();
    }

    //@@ author ZIZI-czh
    @Test
    public void testAddWorkoutToList() {
        Date date = new Date();
        Day day = new Day(date);
        workoutList.addWorkoutToList(date, day);
        workouts.put(date, day);
        assertEquals(workouts, workoutList.getWorkouts());
    }

    //@@ author ZIZI-czh
    @Test
    public void testGetWorkoutsInSpecificWeek() {
        Date date = new Date();
        Day day = new Day(date);
        workoutList.addWorkoutToList(date, day);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date startOfWeekDate = calendar.getTime();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date endOfWeekDate = calendar.getTime();
        HashMap<Date, Day> workoutsInSpecificWeek = workoutList.getWorkoutsInSpecificWeek(startOfWeekDate);
        assertTrue(workoutsInSpecificWeek.containsKey(date));
        assertFalse(workoutsInSpecificWeek.containsKey(endOfWeekDate));
    }

}

