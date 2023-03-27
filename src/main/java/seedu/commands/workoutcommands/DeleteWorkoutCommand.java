package seedu.commands.workoutcommands;

import seedu.commands.Command;

import java.util.Date;


public class DeleteWorkoutCommand extends Command {
    Date workoutToDeleteDate;
    public DeleteWorkoutCommand(Date workoutToDeleteDate) {
        this.workoutToDeleteDate = workoutToDeleteDate;
    }

    @Override
    public void execute() {
        workoutList.removeWorkout(workoutToDeleteDate);
    }
}

