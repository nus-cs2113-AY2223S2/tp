package seedu.commands.workoutcommands;


import seedu.commands.Command;
import seedu.parser.DateFormatter;
import seedu.workout.Workout;

import java.util.Date;

public class ViewWorkoutCommand extends Command {
    private static final String WORKOUT_NOT_FOUND_MESSAGE = "No workout done on ";
    private final Date workoutToViewDate;


    public ViewWorkoutCommand(Date workoutToViewDate) {
        this.workoutToViewDate = workoutToViewDate;
    }

    @Override
    public String execute() {
        for (Workout workout : workoutList.getWorkoutArrayList()) {
            if  (workout.getDate().equals(workoutToViewDate)) {
                return workout.toString();
            }
        }

        return WORKOUT_NOT_FOUND_MESSAGE + DateFormatter.dateToString(workoutToViewDate);
    }
}
