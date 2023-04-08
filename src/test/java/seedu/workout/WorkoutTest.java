package seedu.workout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

//@@ author ZIZI-czh
public class WorkoutTest {
    private Workout workout;
    private Exercise exercise1;
    private Exercise exercise2;

    //@@ author ZIZI-czh
    @BeforeEach
    public void setUp() {
        workout = new Workout("Workout 1");
        exercise1 = new Exercise("Bench Press", "100kg", "8 6 4 8");
        exercise2 = new Exercise("Squats", "80kg", "10 10 10 10");
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

    //@@ author ZIZI-czh
    @Test
    public void testToString() {
        //Workout workout = new Workout(work);
        workout.addExercise(exercise1);
        workout.addExercise(exercise2);
        String expected = "Workout: Workout 1" +
                System.lineSeparator() +
                "Bench Press " +
                "100kg 8 6 4 8" +
                System.lineSeparator() +
                "Squats" +
                " 80kg 10 10 10 10" +
                System.lineSeparator();
        Assertions.assertEquals(expected, workout.toString());
    }

    //@@ author ZIZI-czh
    @Test
    public void testToStringEmpty() {
        String expected = "Workout: Workout 1" +
                System.lineSeparator();

        Assertions.assertEquals(expected, workout.toString());
    }
}

