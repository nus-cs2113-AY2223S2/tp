package seedu.commands.workoutcommands;


import seedu.commands.Command;
import seedu.parser.DateFormatter;
import seedu.workout.Day;

import java.util.Date;
import java.util.HashMap;

public class DeleteWorkoutCommand extends Command {
    private final Date workoutToDeleteDate;
    private final String DELETE_WORKOUT_FIRST = "The workouts for ";
    private final String DELETE_WORKOUT_SECOND = "have been deleted ";

    private final String NO_SUCH_DAY = "no such day has been found";



    public DeleteWorkoutCommand(Date workoutToDeleteDate) {
        this.workoutToDeleteDate = workoutToDeleteDate;
    }

    @Override
    public String execute() {
        /*if (workoutList == null) {
            return "WorkoutList is null.";
        }
        return workoutList.deleteWorkout(workoutToDeleteDate);
    }*/
        HashMap<Date, Day> workouts = workoutList.getWorkouts();

            String formattedDate = DateFormatter.dateToString(workoutToDeleteDate);
            if (workouts.containsKey(workoutToDeleteDate)) {
                workouts.remove(workoutToDeleteDate);
                return "Day " + formattedDate + " has been deleted.";
            } else {
                return "Could not delete day " + formattedDate;
            }
        }
}


