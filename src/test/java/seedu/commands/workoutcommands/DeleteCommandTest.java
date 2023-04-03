package seedu.commands.workoutcommands;

import org.junit.jupiter.api.Test;
import seedu.parser.DateFormatter;
import seedu.workout.Day;
import seedu.workout.WorkoutList;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class DeleteCommandTest {
    private WorkoutList workoutList;

    @Test
    void testExecute() throws ParseException {

        WorkoutList workoutList = new WorkoutList();
        String stringDate1 = "01/11/22";
        String stringDate2 = "01/12/22";
        Date date1 = DateFormatter.stringToDate(stringDate1);
        Date date2 = DateFormatter.stringToDate(stringDate2);
        Day day1 = new Day(date1);
        Day day2 = new Day(date2);
        workoutList.addWorkoutToList(date1, day1);
        workoutList.addWorkoutToList(date2, day2);
        DeleteWorkoutCommand command = new DeleteWorkoutCommand(workoutList, date1);

        String expectedMessage = "Day " + DateFormatter.dateToString(date1) + " have been deleted ";
        String actualMessage = command.execute();

        assertFalse(workoutList.getWorkouts().containsKey(date1));
        assertTrue(workoutList.getWorkouts().containsKey(date2));
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testExecuteNoSuchDay() {
        HashMap<Date, Day> workouts = new HashMap<>();
        Date date = new Date();
        DeleteWorkoutCommand command = new DeleteWorkoutCommand(date);

        String expectedMessage = DateFormatter.dateToString(date) + " does not exit";
        String actualMessage = command.execute();

        assertTrue(workouts.isEmpty());
        assertEquals(expectedMessage, actualMessage);
    }
}
