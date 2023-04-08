package seedu.commands;

import seedu.calorietracker.CalorieTracker;
import seedu.calorietracker.FoodList;
import seedu.exceptions.InvalidArgumentException;

import seedu.workout.WorkoutList;

//@@author calebcjl
/**
 * Represents a command entered by user.
 */
public class Command {
    protected WorkoutList workoutList;
    protected CalorieTracker calorieTracker;
    protected FoodList foodList;

    public Command() {
    }

    /**
     * Sets data for commands to execute on.
     *
     * @param workoutList Workout list.
     * @param calorieTracker Calorie tracker.
     * @param foodList Food list.
     */
    public void setData(WorkoutList workoutList, CalorieTracker calorieTracker, FoodList foodList) {
        this.workoutList = workoutList;
        this.calorieTracker = calorieTracker;
        this.foodList = foodList;
    }

    public String execute() throws InvalidArgumentException {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }
}
