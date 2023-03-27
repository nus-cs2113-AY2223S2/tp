package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.workouttracker.workout.Workout;
import seedu.workouttracker.workout.WorkoutList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

<<<<<<<< HEAD:src/test/java/seedu/commands/ListWorkoutCommandTest.java
public class ListWorkoutCommandTest {

========
public class ListCommandTest {
>>>>>>>> 7ac8556 (Refactor the code edit Junit Test for list and delete calss):src/test/java/seedu/commands/ListCommandTest.java
    //@@ author ZIZI-czh
    @Test
    public void testShowWorkoutList() throws ParseException {
        // Create a new instance of the class that contains the showWorkoutList() method
        // and add some sample data to the workoutList
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");

        // Redirect the console output to a stream for testing
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method to be tested
        // WorkoutList addList = new WorkoutList();
        WorkoutList workoutList = new WorkoutList();
        workoutList.addWorkout(new Workout(format.parse("11/11/23")));
        workoutList.addWorkout(new Workout(format.parse("11/10/23")));
        Command command = new Command();
        command.setData(workoutList);
        ListCommand testList = new ListCommand();
        assertEquals(2, workoutList.workoutArrayList.size());

        testList.setData(workoutList);
        testList.execute();

        // Assert that the console output matches the expected output
        assertEquals("Here are the list of dates for your workout: " + System.lineSeparator()
                        + "11-11-23" + System.lineSeparator()
                        + "11-10-23" + System.lineSeparator()
                        + "----------------------------------" + System.lineSeparator(),
                outContent.toString());

    }
}
