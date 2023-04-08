package seedu.calorietracker;

import seedu.storage.Storage;

import java.util.HashMap;

public class FoodList {
    private final HashMap<String, Integer> foodCalories;

    public FoodList() {
        foodCalories = new HashMap<>();
    }

    public FoodList(Storage storage) {
        this.foodCalories = storage.readFoodListFile();
    }
    public void addFood(String name, int calories) {
        foodCalories.put(name, calories);
    }

    public boolean contains(String food) {
        return foodCalories.containsKey(food);
    }

    public HashMap<String, Integer> getFoodCalories() {
        return foodCalories;
    }
}
