package seedu.workout;

import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutTest {
    public SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

    @Test
    public void testGetDateAndSetDate() throws Exception {
        String expectedDatestring = "05/02/23";
        Date expectedDate = dateFormat.parse(expectedDatestring);
        Workout workout = new Workout(expectedDate);
        assertEquals(expectedDate, workout.getDate());

        String newDatestring = "06/03/24";
        Date newDate = dateFormat.parse(newDatestring);
        workout.setDate(String.valueOf(newDate));
        assertEquals(newDate, workout.getDate());
    }
}
