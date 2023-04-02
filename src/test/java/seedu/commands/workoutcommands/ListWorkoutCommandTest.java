package seedu.commands.workoutcommands;


import org.junit.jupiter.api.Test;
import seedu.parser.DateFormatter;
import seedu.workout.Day;
import seedu.workout.WorkoutList;
import java.text.ParseException;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;


/*public class ListWorkoutCommandTest {

    //@@ author ZIZI-czh
    @Test
    public void testShowWorkoutList() throws ParseException {
        // Create a new instance of the class that contains the showWorkoutList() method
        // and add some sample data to the workoutList
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");

        // Call the method to be tested
       // WorkoutList workoutList = Command.getWorkoutList();
        WorkoutList workoutList = new WorkoutList();
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
    }*/
public class ListWorkoutCommandTest {

    @Test
    public void testExecute_withEmptyWorkoutList_returnsEmptyDayListMessage() {
        WorkoutList workoutList = new WorkoutList();
        ListWorkoutCommand listWorkoutCommand = new ListWorkoutCommand(workoutList);
        String expectedOutput = "No days have been found in the list";
        String actualOutput = listWorkoutCommand.execute();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testExecute_withNonEmptyWorkoutList_returnsListOfDates() throws ParseException {
        WorkoutList workoutList = new WorkoutList();
        String stringDate1 = "01/11/2022";
        String stringDate2 = "02/11/2022";
        Date date1 = DateFormatter.stringToDate(stringDate1);
        Date date2 = DateFormatter.stringToDate(stringDate2);
        Day day1 = new Day(date1);
        Day day2 = new Day(date2);
        workoutList.addWorkoutToList(date1, day1);
        workoutList.addWorkoutToList(date2, day2);
        ListWorkoutCommand listWorkoutCommand = new ListWorkoutCommand(workoutList);

        String expectedOutput = "Here is the list of dates of your workouts:" + System.lineSeparator()
                + DateFormatter.dateToString(date1) + System.lineSeparator()
                + DateFormatter.dateToString(date2) + System.lineSeparator()
                + "----------------------------------";
        String actualOutput = listWorkoutCommand.execute();

        assertEquals(expectedOutput, actualOutput);
    }
}
