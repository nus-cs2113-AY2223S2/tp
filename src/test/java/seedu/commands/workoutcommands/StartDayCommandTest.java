package seedu.commands.workoutcommands;

import org.junit.jupiter.api.Test;
import seedu.parser.DateFormatter;
import seedu.workout.Day;
import seedu.workout.WorkoutList;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


class StartDayCommandTest {
    private WorkoutList workoutList;
    private StartDayCommand startDayCommand;

    @Test
    void testExecuteNewWorkout() throws ParseException {
        String stringDate1 = "01/11/2022";
        Date date1 = DateFormatter.stringToDate(stringDate1);
        workoutList = new WorkoutList();
        startDayCommand = new StartDayCommand(date1);
        String expectedOutput = "Great! You have added a new workout for "
                + DateFormatter.dateToString(date1);
        String actualOutput = startDayCommand.execute();
        assertEquals(expectedOutput, actualOutput);
        workoutList.addWorkoutToList(date1, new Day(date1));
        assertTrue(workoutList.getWorkouts().containsKey(date1));
    }

    @Test
    void testExecuteExistingWorkout() throws ParseException {
        String stringDate2 = "02/11/2022";
        Date date2 = DateFormatter.stringToDate(stringDate2);
        Day existingDay = new Day(date2);
        WorkoutList workoutList = new WorkoutList();
        HashMap<Date, Day> workouts = workoutList.getWorkouts();
        workouts.put(date2, existingDay);
        StartDayCommand command = new StartDayCommand(date2);
        String expectedOutput = "Great! You have added a new workout for "
                + DateFormatter.dateToString(date2);
        String actualOutput = command.execute();
        assertEquals(expectedOutput, actualOutput);
        assertEquals(existingDay, workoutList.getWorkouts().get(date2));
    }


}
