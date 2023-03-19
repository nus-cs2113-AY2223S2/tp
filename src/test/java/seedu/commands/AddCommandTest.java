package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.workout.Exercise;
import seedu.workout.Workout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class AddCommandTest {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
    private static final Date date;

    static {
        try {
            date = dateFormat.parse("10/11/22");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Workout currentWorkout = new Workout(date);

    @Test
    public void testAddCommand() {
        Exercise toAdd = new Exercise("Bench Press", "100kg", "5 5 4 3");
        currentWorkout.addExercise(toAdd);
        assertEquals(toAdd, currentWorkout.getExercises().get(0));
    }
}
