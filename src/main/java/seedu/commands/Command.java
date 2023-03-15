package seedu.commands;

import seedu.workout.WorkoutList;

public class Command {
    protected WorkoutList workoutList;

    protected Command() {
    }

    public void setData(WorkoutList workoutList) {
        this.workoutList = workoutList;
    }

    public void execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }
}
