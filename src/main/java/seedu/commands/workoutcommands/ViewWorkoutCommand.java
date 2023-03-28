package seedu.commands.workoutcommands;


import seedu.commands.Command;
import seedu.parser.DateFormatter;

import java.util.Date;

public class ViewWorkoutCommand extends Command {
    private static final String WORKOUT_NOT_FOUND_MESSAGE = "No workout done on ";
    private final Date workoutToViewDate;


    public ViewWorkoutCommand(Date workoutToViewDate) {
        this.workoutToViewDate = workoutToViewDate;
    }

    @Override
    public String execute() {
        try {
            int index = workoutList.getWorkoutArrayList().indexOf(workoutToViewDate);
            return workoutList.getWorkoutArrayList().get(index).toString();
        } catch (NullPointerException e) {
            return WORKOUT_NOT_FOUND_MESSAGE + DateFormatter.dateToString(workoutToViewDate);
        }
    }
}
