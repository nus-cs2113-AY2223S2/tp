package seedu.commands.workoutcommands;

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

//@@author ZIZI-czh
public class DeleteWorkoutCommandTest {

    @Test
    public void testDeleteWorkoutCommand() throws InvalidArgumentException, ParseException {
        ArrayList<Workout> workouts = new ArrayList<>();
        Date date = DateFormatter.stringToDate("01/01/22");
        Workout workout = new Workout(date, "Workout 1");
        Exercise exercise1 = new Exercise("Push-ups", "20kg", "3 5 5");
        Exercise exercise2 = new Exercise("Sit-ups", "5kg", "3 10");
        workout.addExercise(exercise1);
        workout.addExercise(exercise2);
        workouts.add(workout);
        WorkoutList workoutList = new WorkoutList(workouts);
        DeleteWorkoutCommand deleteWorkoutCommand = new DeleteWorkoutCommand(workoutList, 0);
        String expectedOutput = "Deleted Workout 1 on 01/01/22.";
        assertEquals(expectedOutput, deleteWorkoutCommand.execute());
    }
}




