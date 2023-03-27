package seedu.commands.workoutcommands;

import seedu.commands.Command;

import java.util.Date;

public class ViewWorkoutCommand extends Command {
    Date workoutToViewDate;
    public ViewWorkoutCommand(Date workoutToViewDate) {

        this.workoutToViewDate = workoutToViewDate;
    }


    public void execute() {
        workoutList.displayWorkout(workoutToViewDate);
    }
}
