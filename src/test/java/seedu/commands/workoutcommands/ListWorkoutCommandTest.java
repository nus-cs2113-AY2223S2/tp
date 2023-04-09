package seedu.commands.workoutcommands;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.exceptions.InvalidArgumentException;
import seedu.parser.DateFormatter;
import seedu.workout.Exercise;
import seedu.workout.Workout;
import seedu.workout.WorkoutList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


//@@author ZIZI-czh
public class ListWorkoutCommandTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
    private ListWorkoutCommand listWorkoutCommand;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(output));
        WorkoutList workoutList = new WorkoutList();
        listWorkoutCommand = new ListWorkoutCommand(workoutList);
    }

    @Test
    public void execute_emptyList() {
        String expectedOutput = "Workout list is empty";
        assertEquals(expectedOutput, listWorkoutCommand.execute());
    }

    @Test
    public void execute_oneWorkoutInList_listOfOneWorkout() throws ParseException, InvalidArgumentException {
        ArrayList<Workout> workouts = new ArrayList<>();
        Date date = DateFormatter.stringToDate("01/01/2022");
        Workout workout = new Workout(date, "Workout 1");
        Exercise exercise1 = new Exercise("Push-ups", "20kg", "3 5 5");
        Exercise exercise2 = new Exercise("Sit-ups", "5kg", "3 10");
        workout.addExercise(exercise1);
        workout.addExercise(exercise2);
        workouts.add(workout);
        WorkoutList workoutList = new WorkoutList(workouts);
        listWorkoutCommand = new ListWorkoutCommand(workoutList);
        assertFalse(workoutList.isEmptyList());

        String expectedOutput = "Here is the list of dates of your workouts:" + System.lineSeparator()
                + "1. 01/01/22 Workout 1" + System.lineSeparator()
                + "=======================================";
        assertEquals(expectedOutput, listWorkoutCommand.execute());
    }



}
