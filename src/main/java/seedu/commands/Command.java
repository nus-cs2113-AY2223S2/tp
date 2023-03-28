package seedu.commands;

import seedu.calorietracker.CalorieTracker;
import seedu.workouttracker.WorkoutList;

public class Command {

    public WorkoutList workoutList = new WorkoutList();
    protected CalorieTracker calorieTracker;
    protected Command() {
    }

    public void setData(WorkoutList workoutList, CalorieTracker calorieTracker) {
        if (workoutList == null || calorieTracker == null) {
            throw new IllegalArgumentException("WorkoutList cannot be null.");
        }

        this.workoutList = workoutList;
        this.calorieTracker = calorieTracker;
    }

    public String execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }
}
