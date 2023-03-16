package seedu.commands;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DeleteCommand extends Command {
    String workoutToDeleteDate;
    public DeleteCommand(String workoutToDeleteDate) {
        this.workoutToDeleteDate = workoutToDeleteDate;
    }

    @Override
    public void execute() {
        workoutList.removeWorkout(workoutToDeleteDate);
    }
}
