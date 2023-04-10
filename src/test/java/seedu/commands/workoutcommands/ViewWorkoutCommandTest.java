package seedu.commands.workoutcommands;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.exceptions.InvalidArgumentException;
import seedu.parser.DateFormatter;
import seedu.workout.Exercise;
import seedu.workout.Workout;
import seedu.workout.WorkoutList;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


//@@author ZIZI-czh
public class ViewWorkoutCommandTest {
    private WorkoutList workoutList;
    private ViewWorkoutCommand viewWorkoutCommand;

    @BeforeEach
    public void setUp() {
        workoutList = new WorkoutList();
        viewWorkoutCommand = new ViewWorkoutCommand(0);
    }

    @Test
    public void execute_validIndex_returnsCorrectString() throws InvalidArgumentException, ParseException {
        ArrayList<Workout> workouts = new ArrayList<>();
        Date date = DateFormatter.stringToDate("01/01/2022");
        Workout workout = new Workout(date, "Workout 1");
        Exercise exercise1 = new Exercise("Push-ups", "20kg", "3 5 5");
        Exercise exercise2 = new Exercise("Sit-ups", "5kg", "3 10");
        workout.addExercise(exercise1);
        workout.addExercise(exercise2);
        workouts.add(workout);
        WorkoutList workoutList = new WorkoutList(workouts);
        viewWorkoutCommand = new ViewWorkoutCommand(workoutList, 0);
        String expectedOutput = "Here are the list of exercises for " + workouts.get(0).getWorkoutName()
                + " on " + DateFormatter.dateToString(date) + "."
                + System.lineSeparator()
                + "1. Push-ups 20kg 3 5 5"
                + System.lineSeparator()
                + "2. Sit-ups 5kg 3 10"
                + System.lineSeparator()
                + "=======================================";
        assertEquals(expectedOutput, viewWorkoutCommand.execute());
    }

    @Test
    public void execute_invalidIndex_throwsInvalidArgumentException() {
        viewWorkoutCommand = new ViewWorkoutCommand(workoutList, 0);
        assertThrows(InvalidArgumentException.class, () -> viewWorkoutCommand.execute());
    }
}
