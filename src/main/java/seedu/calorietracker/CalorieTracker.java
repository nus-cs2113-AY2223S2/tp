package seedu.calorietracker;

import seedu.storage.Storage;

import java.util.Date;
import java.util.HashMap;

import static seedu.commands.caloriecommands.AddCalorieCommand.CALORIES_NOT_GIVEN;

//@@author calebcjl
/**
 * Represents a calorie tracker.
 */
public class CalorieTracker {
    public static final int CALORIES_NOT_TRACKED = -1;
    private HashMap<Date, Integer> totalCaloriesConsumedInDay;
    private FoodList foodList;

    public CalorieTracker() {
        totalCaloriesConsumedInDay = new HashMap<>();
        foodList = new FoodList();
    }

    public CalorieTracker(Storage storage, FoodList foodList) {
        totalCaloriesConsumedInDay = storage.readCalorieTrackerFile();
        this.foodList = foodList;
    }

    public HashMap<Date, Integer> getTotalCaloriesConsumedInDay() {
        return totalCaloriesConsumedInDay;
    }

    /**
     * Add new calorie consumption to CalorieTracker.
     * If no entry is given for calories, CalorieTracker will check with FoodList for number of calories.
     *
     * @param date Date of consumption
     * @param foodName Name of food consumed.
     * @param calories Number of calories consumed.
     * @return Output message.
     */
    public String addCalories(Date date, String foodName, int calories) {
        if (calories == CALORIES_NOT_GIVEN && !foodList.contains(foodName)) {
            return foodName + " has not been added previously. Please also indicate calorie count.";
        }
        int foodCalories;
        if (calories == CALORIES_NOT_GIVEN) {
            foodCalories = foodList.getFoodCalories().get(foodName);
            foodList.addFood(foodName, calories);
        } else {
            foodCalories = calories;
        }

        if (totalCaloriesConsumedInDay.containsKey(date)) {
            totalCaloriesConsumedInDay.compute(date, (k,v) -> v + foodCalories);
        } else {
            totalCaloriesConsumedInDay.put(date, calories);
        }
        return "Consumed additional " + calories + "kcal." + System.lineSeparator() +
                "Total calories consumed: " + getCalories(date) + "kcal";
    }

    public int getCalories(Date date) {
        return totalCaloriesConsumedInDay.getOrDefault(date, CALORIES_NOT_TRACKED);
    }
}
