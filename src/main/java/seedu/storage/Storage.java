package seedu.storage;

import seedu.calorietracker.CalorieTracker;
import seedu.calorietracker.FoodDictionary;
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
    private final FoodDictionaryStorage foodDictionaryStorage;
    private final CalorieTrackerStorage calorieTrackerStorage;

    public Storage() {
        workoutListStorage = new WorkoutListStorage();
        foodDictionaryStorage = new FoodDictionaryStorage();
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
    public HashMap<String, Integer> readFoodDictionaryFile() {
        return foodDictionaryStorage.getUserData();
    }

    /**
     * Returns calorie tracker saved in user data file.
     *
     * @return Calorie tracker.
     */
    public HashMap<Date, FoodList> readCalorieTrackerFile() {
        return calorieTrackerStorage.getUserData();
    }
    public void saveUserData(WorkoutList workoutList, FoodDictionary foodDictionary, CalorieTracker calorieTracker) {
        try {
            workoutListStorage.saveUserData(workoutList.getWorkouts());
            foodDictionaryStorage.saveUserData(foodDictionary.getFoodCalories());
            calorieTrackerStorage.saveUserData(calorieTracker.getDailyFoodConsumption());
        } catch (IOException e) {
            Ui.showSaveUserDataErrorMessage();
        }
    }
}
