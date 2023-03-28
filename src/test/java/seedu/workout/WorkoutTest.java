package seedu.workout;

import org.junit.jupiter.api.Test;
import seedu.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutTest {
    public SimpleDateFormat dateFormatting = new SimpleDateFormat("dd/MM/yyyy");

    @Test
    public void testGetDateAndSetDate() throws Exception {
        String expectedDatestring = "05/02/2023";
        Date expectedDate = dateFormatting.parse(expectedDatestring);
        Workout workout = new Workout(expectedDate);
        DateFormat dateFormat1 = new DateFormat(expectedDate);
        String formattedDate1 = dateFormat1.formatDate();
        assertEquals(formattedDate1, workout.getStringDate());

        String newDatestring = "06/03/2024";
        Date newDate = dateFormatting.parse(newDatestring);
        DateFormat dateFormat2 = new DateFormat(newDate);
        String formattedDate2 = dateFormat2.formatDate();
        workout.setDate(newDate);
        assertEquals(formattedDate2, workout.getStringDate());
    }


}
