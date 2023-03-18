package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.workout.Workout;
import seedu.workout.WorkoutList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {

    //@@ author ZIZI-czh
    @Test
    public void testShowWorkoutList() throws ParseException {
        // Create a new instance of the class that contains the showWorkoutList() method
        // and add some sample data to the workoutList

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        Date date = format.parse("11/11/23");


        // Redirect the console output to a stream for testing
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));


        // Call the method to be tested
        WorkoutList testList = new WorkoutList();
        testList.addWorkout(new Workout(date));
        testList.showWorkoutList();

        // Assert that the console output matches the expected output
        assertEquals("Here are the list of dates for your workout: \n11-11-23",
                outContent.toString());

    }
}
