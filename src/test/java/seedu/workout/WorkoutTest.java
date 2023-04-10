package seedu.workout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.parser.DateFormatter;
import seedu.ui.Ui;

import java.util.ArrayList;
import java.util.Date;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

//@@ author ZIZI-czh
public class WorkoutTest {
    private static final Date date = new Date();
    private static final String WORKOUT_NAME = "Test Workout";
    private Workout workout;
    private Exercise exercise1;
    private Exercise exercise2;



    //@@ author ZIZI-czh
    @BeforeEach
    public void setUp() {
        workout = new Workout();
        exercise1 = new Exercise("Bench Press", "100kg", "8 6 4 8");
        exercise2 = new Exercise("Squats", "80kg", "10 10 10 10");
    }

    @Test
    public void testGetWorkoutName() {
        Workout workout = new Workout(date, WORKOUT_NAME);
        assertEquals(WORKOUT_NAME, workout.getWorkoutName());
    }


    @Test
    public void testGetDate() {
        Workout workout = new Workout(date, WORKOUT_NAME);
        assertEquals(date, workout.getDate());
    }

    @Test
    public void testGetExercises() {
        Workout workout = new Workout(date, WORKOUT_NAME);
        ArrayList<Exercise> exercises = workout.getExercises();
        assertTrue(exercises.isEmpty());
    }
    //@@ author ZIZI-czh
    @Test
    public void testAddExercise() {
        workout.addExercise(exercise1);
        ArrayList<Exercise> exercises = workout.getExercises();
        Assertions.assertEquals(1, exercises.size());
        Assertions.assertEquals(exercise1, exercises.get(0));

        workout.addExercise(exercise2);
        exercises = workout.getExercises();
        Assertions.assertEquals(2, exercises.size());
        Assertions.assertEquals(exercise2, exercises.get(1));
    }

    @Test
    public void testToString() {
        Workout workout = new Workout(date, WORKOUT_NAME);
        Exercise exercise1 = new Exercise("Test Exercise 1", "36kg", "4 4 4");
        Exercise exercise2 = new Exercise("Test Exercise 2", "50kg", "4 4  6 5");
        workout.addExercise(exercise1);
        workout.addExercise(exercise2);

        String expected = "Here are the list of exercises for Test Workout on "
                + DateFormatter.dateToString(date) + '.' + System.lineSeparator()
                + "1. Test Exercise 1 36kg 4 4 4" + System.lineSeparator()
                + "2. Test Exercise 2 50kg 4 4  6 5" + System.lineSeparator()
                + Ui.line();
        assertEquals(expected, workout.toString());
    }

}

