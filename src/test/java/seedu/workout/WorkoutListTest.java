package seedu.workout;


import org.junit.jupiter.api.Test;
import seedu.parser.DateFormatter;
import seedu.ui.Ui;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;



//@@author ZIZI-czh
class WorkoutListTest {
    @Test
    public void testStartWorkout() throws Exception {
        // Initialize a new WorkoutList
        WorkoutList workoutList = new WorkoutList();

        // Check that the list is empty
        assertTrue(workoutList.isEmptyList());

        // Add a new workout
        Date date = new Date();
        workoutList.startWorkout(date, "Workout 1");

        // Check that the list is not empty
        assertFalse(workoutList.isEmptyList());

        // Check that the current workout index is set to the correct value
        assertEquals(0, workoutList.getCurrentWorkoutIndex());

        // Check that the workout was added to the list
        ArrayList<Workout> workouts = workoutList.getWorkouts();
        assertEquals(1, workouts.size());
        assertEquals(date, workouts.get(0).getDate());
        assertEquals("Workout 1", workouts.get(0).getWorkoutName());
    }

    @Test
    public void testGetWorkoutsInSpecificWeek() throws Exception {
        // Initialize a new WorkoutList
        WorkoutList workoutList = new WorkoutList();

        // Add some workouts to the list
        Date date1 = new Date();
        workoutList.startWorkout(date1, "Workout 1");
        Date date2 = new Date(date1.getTime() + 604800000); // Add one week to date1
        workoutList.startWorkout(date2, "Workout 2");
        Date date3 = new Date(date2.getTime() + 604800000); // Add one week to date2
        workoutList.startWorkout(date3, "Workout 3");

        // Get the workouts for the second week
        ArrayList<Workout> workouts = workoutList.getWorkoutsInSpecificWeek(date2);

        // Check that the correct number of workouts were returned
        assertEquals(1, workouts.size());

        // Check that the correct workout was returned
        assertEquals(date2, workouts.get(0).getDate());
        assertEquals("Workout 2", workouts.get(0).getWorkoutName());
    }

    @Test
    public void testCountSetsReps() throws Exception {
        ArrayList<Workout> workouts = new ArrayList<>();
        Date date1 = DateFormatter.stringToDate("4/4/23");
        Date date2 = DateFormatter.stringToDate("6/4/23");
        Date date3 = DateFormatter.stringToDate("8/4/23");

        // create workout 1
        Workout workout1 = new Workout(date1, "Workout 1");
        Exercise exercise1 = new Exercise("BenchPress", "45kg", "5 5 5 5");
        Exercise exercise2 = new Exercise("Squats", "50kg", "6 6 6 6");
        workout1.addExercise(exercise1);
        workout1.addExercise(exercise2);
        workouts.add(workout1);

        // create workout 2
        Workout workout2 = new Workout(date2, "Workout 2");
        Exercise exercise3 = new Exercise("Bench Press", "45kg", "2 2 2 2");
        Exercise exercise4 = new Exercise("Dead lifts", "45kg", "5 5 5 5");
        workout2.addExercise(exercise3);
        workout2.addExercise(exercise4);
        workouts.add(workout2);

        // create workout 3
        Workout workout3 = new Workout(date3, "Workout 3");
        Exercise exercise5 = new Exercise("Squats", "45kg", "5 5 6 5 5");
        Exercise exercise6 = new Exercise("Dead lifts", "65kg", "5 5 5 6 5");
        workout3.addExercise(exercise5);
        workout3.addExercise(exercise6);
        workouts.add(workout3);

        // create workout list
        WorkoutList workoutList = new WorkoutList(workouts);

        // count sets and reps for week of April 3, 2023
        Date weekStart = DateFormatter.stringToDate("3/4/23");
        String result = workoutList.countSetsReps(weekStart);

        // expected output
        String expected = "Information of exercises for the week of 03/04/23" +
                System.lineSeparator() +
                "Name: Squats, sets: 9, rps: 50" +
                System.lineSeparator() +
                "Name: Bench Press, sets: 4, rps: 8" +
                System.lineSeparator() +
                "Name: Dead lifts, sets: 9, rps: 46" +
                System.lineSeparator() +
                "Name: BenchPress, sets: 4, rps: 20" +
                System.lineSeparator() +
                Ui.line();

        // check if expected output matches actual output
        assertEquals(expected, result);
    }
}
