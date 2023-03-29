package seedu.commands.workoutcommands;


import org.junit.jupiter.api.Test;
import seedu.commands.Command;
import seedu.workout.Workout;
import seedu.workout.WorkoutList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
        WorkoutList workoutList = Command.getWorkoutList();
        workoutList.addWorkout(new Workout(format.parse("11/11/23")));
        workoutList.addWorkout(new Workout(format.parse("11/10/23")));
        ListWorkoutCommand testList = new ListWorkoutCommand();
        //testList.setData(workoutList, calorieTracker);
        assertEquals(2, workoutList.getWorkoutArrayList().size());

        //Capture the console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method to be tested
        testList.execute();

        // Assert that the console output matches the expected output
        assertEquals("Here are the list of dates of your workouts:" + System.lineSeparator()
                        + "1. 11/11/23" + System.lineSeparator()
                        + "2. 11/10/23" + System.lineSeparator(),
                outContent.toString());
    }
}
