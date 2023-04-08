package seedu.commands;

import seedu.calorietracker.CalorieTracker;
import seedu.calorietracker.FoodDictionary;
import seedu.exceptions.InvalidArgumentException;
import seedu.exceptions.InvalidSyntaxException;
import seedu.workout.WorkoutList;

//@@author calebcjl
/**
 * Represents a command entered by user.
 */
public class Command {

    protected WorkoutList workoutList;
    protected CalorieTracker calorieTracker;
    protected FoodDictionary foodDictionary;

    public Command() {
    }

    /**
     * Sets data for commands to execute on.
     *
     * @param workoutList Workout list.
     * @param calorieTracker Calorie tracker.
     * @param foodDictionary Food list.
     */
    public void setData(WorkoutList workoutList, CalorieTracker calorieTracker, FoodDictionary foodDictionary) {
        this.workoutList = workoutList;
        this.calorieTracker = calorieTracker;
        this.foodDictionary = foodDictionary;
    }

    public String execute() throws InvalidArgumentException, InvalidSyntaxException {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }
}
