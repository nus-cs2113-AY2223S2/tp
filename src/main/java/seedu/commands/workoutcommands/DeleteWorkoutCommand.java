package seedu.commands.workoutcommands;


import seedu.commands.Command;
import seedu.parser.DateFormatter;
import seedu.workout.Day;
import seedu.workout.WorkoutList;

import java.util.Date;
import java.util.HashMap;

//@@ author ZIZI-czh
public class DeleteWorkoutCommand extends Command {
    private static final String DELETE_WORKOUT_FIRST = "Day ";
    private static final String DELETE_WORKOUT_SECOND = " have been deleted ";
    private static final String NO_SUCH_DAY = " does not exit";
    private final Date workoutToDeleteDate;

    //@@ author ZIZI-czh
    public DeleteWorkoutCommand(Date workoutToDeleteDate) {
        super();
        this.workoutToDeleteDate = workoutToDeleteDate;
    }
    //@@ author ZIZI-czh
    public DeleteWorkoutCommand(WorkoutList workoutList, Date workoutToDeleteDate) {
        this.workoutList = workoutList;
        this.workoutToDeleteDate = workoutToDeleteDate;
    }

    //@@ author ZIZI-czh
    @Override
    public String execute() {

        HashMap<Date, Day> workouts = workoutList.getWorkouts();
        String formattedDate = DateFormatter.dateToString(workoutToDeleteDate);
        if (workouts.containsKey(workoutToDeleteDate)) {
            workouts.remove(workoutToDeleteDate);
            return DELETE_WORKOUT_FIRST + formattedDate + DELETE_WORKOUT_SECOND;
        } else {
            return formattedDate + NO_SUCH_DAY;
        }
    }
}


