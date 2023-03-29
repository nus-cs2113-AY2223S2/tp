package seedu.workout;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
//@@ Author Richardtok
public class WorkoutViewTest {
    @Test
    public void viewFunctionTest (){

        WorkoutList workoutList = new WorkoutList();
        Calendar calendar0 = Calendar.getInstance();
        calendar0.set(2021, Calendar.MARCH, 31); // Saturday
        workoutList.addWorkout(new Workout(calendar0.getTime()));
        Exercise exercise = new Exercise("Lateral Raise", "10kg", "8 8 9");
        String expected = "Here are the list of exercises in your workout:\n" +
                "1. Lateral Raise 10kg 8 8 9";
        String actual = "Here are the list of exercises in your workout:\n" +
                "1. Lateral Raise 10kg 8 8 9";
        assertEquals(expected, actual);
    }

}
