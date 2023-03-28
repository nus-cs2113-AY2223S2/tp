package seedu.workout;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciseTest {

    @Test
    public void testGetSetsCount() {
        Exercise exercise = new Exercise("deadlift", "100", "8 8 9");
        int expected = 3;
        int actual = exercise.getSetsCount();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetRepsCount() {
        Exercise exercise1 = new Exercise("Bench Press", "100kg", "8 6 4");
        Exercise exercise2 = new Exercise("Squats", "80kg", "10 10 10 10");

        assertEquals(18, exercise1.getRepsCount());
        assertEquals(40, exercise2.getRepsCount());
    }
}

