package seedu.commands;

import seedu.calorietracker.CalorieTracker;
import seedu.workouttracker.workout.WorkoutList;

public class Command {
    protected WorkoutList workoutList;
    protected CalorieTracker calorieTracker;
    protected Command() {
    }

    public void setData(WorkoutList workoutList, CalorieTracker calorieTracker) {
        this.workoutList = workoutList;
        this.calorieTracker = calorieTracker;
    }

    public void execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }
}
