package seedu.workout;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.parser.DateFormatter;
import seedu.workout.Day;
import seedu.workout.WorkoutList;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testGetWorkoutsInSpecificWeek() throws ParseException {
        String stringDate = "01/11/22";
        Date date = DateFormatter.stringToDate(stringDate);
        Day day = new Day(date);
        workoutList.addWorkoutToList(date, day);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date startOfWeekDate = calendar.getTime();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date endOfWeekDate = calendar.getTime();
        HashMap<Date, Day> workoutsInSpecificWeek = workoutList.getWorkoutsInSpecificWeek(startOfWeekDate);
        Assertions.assertTrue(workoutsInSpecificWeek.containsKey(date));
        Assertions.assertFalse(workoutsInSpecificWeek.containsKey(endOfWeekDate));
    }

}