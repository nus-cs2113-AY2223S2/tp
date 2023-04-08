package seedu.storage;

import seedu.calorietracker.CalorieTracker;
import seedu.calorietracker.FoodList;
import seedu.ui.Ui;
import seedu.workout.Workout;
import seedu.workout.WorkoutList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

//@@author calebcjl
/**
 * Represents the main storage for all storage classes.
 */
public class Storage {
    private final WorkoutListStorage workoutListStorage;
    private final FoodListStorage foodListStorage;
    private final CalorieTrackerStorage calorieTrackerStorage;

    public Storage() {
        workoutListStorage = new WorkoutListStorage();
        foodListStorage = new FoodListStorage();
        calorieTrackerStorage = new CalorieTrackerStorage();
    }

    /**
     * Returns list of workouts saved in user data file.
     *
     * @return List of workouts.
     */
    public ArrayList<Workout> readWorkoutListFile() {
        return workoutListStorage.getUserData();
    }

    /**
     * Returns food list saved in user data file.
     *
     * @return Food list.
     */
    public HashMap<String, Integer> readFoodListFile() {
        return foodListStorage.getUserData();
    }

    /**
     * Returns calorie tracker saved in user data file.
     *
     * @return Calorie tracker.
     */
    public HashMap<Date,Integer> readCalorieTrackerFile() {
        return calorieTrackerStorage.getUserData();
    }
    public void saveUserData(WorkoutList workoutList, FoodList foodList, CalorieTracker calorieTracker) {
        try {
            workoutListStorage.saveUserData(workoutList.getWorkouts());
            foodListStorage.saveUserData(foodList.getFoodCalories());
            calorieTrackerStorage.saveUserData(calorieTracker.getTotalCaloriesConsumedInDay());
        } catch (IOException e) {
            Ui.showSaveUserDataErrorMessage();
        }
    }
}
