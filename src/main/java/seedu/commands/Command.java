package seedu.commands;

import seedu.calorietracker.CalorieTracker;
import seedu.workouttracker.workout.WorkoutList;

public class Command {
<<<<<<< HEAD
    protected WorkoutList workoutList;
    protected CalorieTracker calorieTracker;
    protected Command() {
    }

    public void setData(WorkoutList workoutList, CalorieTracker calorieTracker) {
=======
    public WorkoutList workoutList = new WorkoutList();
    protected Command() {
    }

    public void setData(WorkoutList workoutList) {
        if (workoutList == null) {
            throw new IllegalArgumentException("WorkoutList cannot be null.");
        }
>>>>>>> 7ac8556 (Refactor the code edit Junit Test for list and delete calss)
        this.workoutList = workoutList;
        this.calorieTracker = calorieTracker;
    }

    public void execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }
}
