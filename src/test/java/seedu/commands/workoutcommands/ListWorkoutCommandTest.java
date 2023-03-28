package seedu.commands.workoutcommands;

import org.junit.jupiter.api.Test;
import seedu.calorietracker.CalorieTracker;
import seedu.workouttracker.Workout;
import seedu.workouttracker.WorkoutList;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ListWorkoutCommandTest {

    //@@ author ZIZI-czh
    @Test
    public void testShowWorkoutList() throws ParseException {
        // Create a new instance of the class that contains the showWorkoutList() method
        // and add some sample data to the workoutList
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");

        // Call the method to be tested
        // WorkoutList addList = new WorkoutList();
        WorkoutList workoutList = new WorkoutList();
        CalorieTracker calorieTracker = new CalorieTracker();
        workoutList.addWorkout(new Workout(format.parse("11/11/23")));
        workoutList.addWorkout(new Workout(format.parse("11/10/23")));
        ListWorkoutCommand testList = new ListWorkoutCommand();
        testList.setData(workoutList, calorieTracker);
        assertEquals(2, workoutList.getWorkoutArrayList().size());

        // Assert that the console output matches the expected output
        assertEquals("Here are the list of dates of your workouts:" + System.lineSeparator()
                        + "1. 11/11/23" + System.lineSeparator()
                        + "2. 11/10/23" + System.lineSeparator(),
                testList.execute());

    }
}

