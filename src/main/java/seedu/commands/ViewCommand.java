package seedu.commands;

import java.util.Date;

public class ViewCommand extends Command {
    Date workoutToViewDate;
    public ViewCommand(Date workoutToViewDate) {

        this.workoutToViewDate = workoutToViewDate;
    }


    public void execute() {
        workoutList.displayWorkout(workoutToViewDate);
    }
}
