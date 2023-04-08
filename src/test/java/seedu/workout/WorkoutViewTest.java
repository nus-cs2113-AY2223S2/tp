package seedu.workout;

import seedu.commands.workoutcommands.ViewWorkoutCommand;
import seedu.parser.DateFormatter;
import seedu.ui.Ui;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@ author ZIZI-czh
public class WorkoutViewTest {
    private WorkoutList workoutList;
    private ViewWorkoutCommand command;

    @BeforeEach
    public void setUp() {
        workoutList = new WorkoutList();
    }

    //@@ author ZIZI-czh
    @Test
    public void testExecute_workoutFound_returnsWorkoutString() throws ParseException {
        HashMap<String, Workout> singleWorkout = new HashMap<>();
        Workout workout = new Workout("Workout 1");
        Exercise exercise1 = new Exercise("Bench Press", "100kg", "8 6 4 5");
        Exercise exercise2 = new Exercise("Squats", "80kg", "10 10 10 10");
        workout.addExercise(exercise1);
        workout.addExercise(exercise2);
        String stringDate1 = "01/11/2022";
        Date date1 = DateFormatter.stringToDate(stringDate1);
        Day day = new Day(date1);
        day.addWorkout("Workout 1", workout);
        //day.setWorkoutsByDate(date1, workout);;
        workoutList.addWorkoutToList(date1, day);
        command = new ViewWorkoutCommand(date1, workoutList);
        // Execute the command
        String result = command.execute();

        // Assert that the result contains the expected output
        String expectedOutput = "Workouts on " + DateFormatter.dateToString(date1) + ":" + System.lineSeparator()
                + "Workout Name: " + "Workout 1" + System.lineSeparator()
                + "Exercise Info: " + System.lineSeparator()
                + "1. Name: " + exercise1.getName() + ", weight: "
                + exercise1.getWeight() + ", rps: " + exercise1.getRepsPerSet()
                + System.lineSeparator()
                + "2. Name: " + exercise2.getName() + ", weight: "
                + exercise2.getWeight() + ", rps: " + exercise2.getRepsPerSet()
                + System.lineSeparator() + Ui.lineSeparator() + System.lineSeparator();
        assertEquals(expectedOutput, result);
    }
    //@@ author ZIZI-czh
    @Test
    public void testExecute_noWorkoutFound_returnsErrorMessage() throws ParseException {
        // Execute the command

        String stringDate1 = "01/12/2022";
        Date date1 = DateFormatter.stringToDate(stringDate1);
        // Assert that the result contains the expected output
        String expectedOutput = DateFormatter.dateToString(date1) + " does not exit in the list";
        ViewWorkoutCommand command1 = new ViewWorkoutCommand(date1);
        String result = command1.execute();
        assertEquals(expectedOutput, result);
    }
}

