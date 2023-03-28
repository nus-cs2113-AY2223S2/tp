package seedu.commands.workoutcommands;


import seedu.commands.Command;

import java.util.Date;

public class DeleteWorkoutCommand extends Command {
    private final Date workoutToDeleteDate;

    public DeleteWorkoutCommand(Date workoutToDeleteDate) {
        this.workoutToDeleteDate = workoutToDeleteDate;
    }

    @Override
    public String execute() {
        if (workoutList == null) {
            return "WorkoutList is null.";
        }
        return workoutList.deleteWorkout(workoutToDeleteDate);
    }
}


