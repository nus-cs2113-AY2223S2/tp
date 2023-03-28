package seedu.workout;

import org.junit.jupiter.api.Test;
import seedu.parser.DateFormatter;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutTest {
    @Test
    public void testGetDateAndSetDate() throws Exception {
        String expectedDatestring = "05/02/2023";
        Date expectedDate = DateFormatter.stringToDate(expectedDatestring);
        Workout workout = new Workout(expectedDate);
        String formattedDate1 = DateFormatter.dateToString(expectedDate);
        assertEquals(formattedDate1, workout.getDateToString());

        String newDatestring = "06/03/2024";
        Date newDate = DateFormatter.stringToDate(newDatestring);
        String formattedDate2 = DateFormatter.dateToString(newDate);
        workout.setDate(newDate);
        assertEquals(formattedDate2, workout.getDateToString());
    }

}
