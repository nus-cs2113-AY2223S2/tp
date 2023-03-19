package seedu.commands;

import java.util.Date;


public class DeleteCommand extends Command {
    Date workoutToDeleteDate;
    public DeleteCommand(Date workoutToDeleteDate) {
        this.workoutToDeleteDate = workoutToDeleteDate;
    }

    @Override
    public void execute() {
        workoutList.removeWorkout(workoutToDeleteDate);
    }
}

