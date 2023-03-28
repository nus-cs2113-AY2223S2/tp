package seedu.commands.workoutcommands;

import org.junit.jupiter.api.Test;
import seedu.calorietracker.CalorieTracker;
import seedu.commands.Command;
import seedu.commands.InvalidCommand;
import seedu.parser.DateFormatter;
import seedu.workouttracker.Workout;
import seedu.workouttracker.WorkoutList;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {

    //@@ author guillaume-grn
    @Test
    public void testRemoveWorkout() throws Exception {
        WorkoutList workoutLists = new WorkoutList();
        CalorieTracker calorieTracker = new CalorieTracker();
        Command command = new InvalidCommand("invalid");

        String stringDate1 = "01/11/2022";
        String stringDate2 = "02/11/2022";
        Date date1 = DateFormatter.stringToDate(stringDate1);
        Date date2 = DateFormatter.stringToDate(stringDate2);
        Workout workout1 = new Workout(date1);
        Workout workout2 = new Workout(date2);
        workoutLists.addWorkout(workout1);
        workoutLists.addWorkout(workout2);
        command.setData(workoutLists, calorieTracker);

        // Check that both workouts are in the list
        assertEquals(2, workoutLists.getWorkoutArrayList().size());

        //DeleteCommand delete = new DeleteCommand(date1);


        DeleteWorkoutCommand delete = new DeleteWorkoutCommand(date1);
        delete.setData(workoutLists, calorieTracker);
        // Remove workout1 from the list
        delete.execute();

        // Check that workout1 was removed and workout2 is still in the list
        assertEquals(1, workoutLists.getWorkoutArrayList().size());
        String formattedDate = DateFormatter.dateToString(date2);
        assertEquals(formattedDate,
                DateFormatter.dateToString(workoutLists.getWorkoutArrayList().get(0).getDate()));

        // Try to remove workout1 again (should not be in the list)
        delete.execute();

        // Check that workout1 was not removed and the list size is still 1
        assertEquals(1, workoutLists.getWorkoutArrayList().size());
    }
}
