package seedu.commands.workoutcommands;


import org.junit.jupiter.api.Test;
import seedu.parser.DateFormatter;
import seedu.workout.Day;
import seedu.workout.WorkoutList;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
        String stringDate1 = "01/11/22";
        // String stringDate2 = "02/11/22";
        Date date1 = DateFormatter.stringToDate(stringDate1);
        // Date date2 = DateFormatter.stringToDate(stringDate2);
        Day day1 = new Day(date1);
        // Day day2 = new Day(date2);
        workoutList.addWorkoutToList(date1, day1);
        //workoutList.addWorkoutToList(date2, day2);
        ListWorkoutCommand listWorkoutCommand = new ListWorkoutCommand(workoutList);

        String expectedOutput = "Here is the list of dates of your workouts:" + System.lineSeparator()
                + DateFormatter.dateToString(date1) + System.lineSeparator()
                //+ DateFormatter.dateToString(date2) + System.lineSeparator()
                + "----------------------------------";
        String actualOutput = listWorkoutCommand.execute();
        assertEquals(expectedOutput, actualOutput);
    }
}
