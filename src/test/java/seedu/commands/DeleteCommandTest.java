package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.workout.Workout;
import seedu.workout.WorkoutList;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {

    public SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");


    //@@ author guillaume-grn
    @Test
    public void testRemoveWorkout() throws Exception {
        WorkoutList workoutLists = new WorkoutList();
        Command command = new Command();

        String stringDate1 = "01/11/22";
        String stringDate2 = "02/11/22";
        Date date1 = dateFormat.parse(stringDate1);
        Date date2 = dateFormat.parse(stringDate2);
        Workout workout1 = new Workout(date1);
        Workout workout2 = new Workout(date2);
        workoutLists.addWorkout(workout1);
        workoutLists.addWorkout(workout2);
        command.setData(workoutLists);

        // Check that both workouts are in the list
        assertEquals(2, workoutLists.workoutArrayList.size());

        //DeleteCommand delete = new DeleteCommand(date1);


        DeleteCommand delete = new DeleteCommand(date1);
        delete.setData(workoutLists);
        // Remove workout1 from the list
        delete.execute();

        // Check that workout1 was removed and workout2 is still in the list
        assertEquals(1, workoutLists.workoutArrayList.size());
        assertEquals(date2, workoutLists.workoutArrayList.get(0).getDate());

        // Try to remove workout1 again (should not be in the list)
        delete.execute();

        // Check that workout1 was not removed and the list size is still 1
        assertEquals(1, workoutLists.workoutArrayList.size());
    }
}
