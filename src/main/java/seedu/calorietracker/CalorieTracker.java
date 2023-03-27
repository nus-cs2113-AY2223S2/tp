package seedu.calorietracker;


import java.util.Date;
import java.util.HashMap;

import static seedu.commands.caloriecommands.AddCalorieCommand.CALORIES_NOT_GIVEN;

public class CalorieTracker {
    public static final int CALORIES_NOT_TRACKED = -1;
    private final HashMap<Date, Integer> totalCaloriesConsumedInDay;
    private FoodList foodList;

    public CalorieTracker() {
        totalCaloriesConsumedInDay = new HashMap<>();
        foodList = new FoodList();
    }
    public CalorieTracker(FoodList foodlist) {
        totalCaloriesConsumedInDay = new HashMap<>();
        this.foodList = foodlist;
    }

    public void addCalories(Date date, String food, int calories) {
        if (calories == CALORIES_NOT_GIVEN && !foodList.contains(food)) {
            System.out.println(food + " has not been added previously. Please also indicate calorie count.");
            return;
        }

        int caloriesInFood;
        if (calories == CALORIES_NOT_GIVEN) {
            caloriesInFood = foodList.getCalories(food);
        } else if (isValidCalories(calories)){
            foodList.addFood(food, calories);
            caloriesInFood = calories;
        } else {
            System.out.println("Calories count is invalid");
            return;
        }

        if (totalCaloriesConsumedInDay.containsKey(date)) {
            totalCaloriesConsumedInDay.compute(date, (key, value) -> value + caloriesInFood);
        } else {
            totalCaloriesConsumedInDay.put(date, caloriesInFood);
        }

        System.out.println("Consumed additional " + caloriesInFood + "kcal." + System.lineSeparator() +
                "Total calories consumed: " + getCalories(date) + "kcal");
    }

    public int getCalories(Date date) {
        return totalCaloriesConsumedInDay.getOrDefault(date, -1);
    }

    private static boolean isValidCalories(int calories) {
        return calories >= 0;
    }
}
