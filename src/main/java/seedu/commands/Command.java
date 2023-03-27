package seedu.commands;

import seedu.calorietracker.CalorieTracker;
import seedu.workouttracker.workout.WorkoutList;

public class Command {

    public WorkoutList workoutList = new WorkoutList();
    protected CalorieTracker calorieTracker;
    protected Command() {
    }

    public void setData(WorkoutList workoutList, CalorieTracker calorieTracker) {


    }

    public void setData(WorkoutList workoutList) {
        if (workoutList == null) {
            throw new IllegalArgumentException("WorkoutList cannot be null.");
        }

        this.workoutList = workoutList;
        this.calorieTracker = calorieTracker;
    }

    public void execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }
}
