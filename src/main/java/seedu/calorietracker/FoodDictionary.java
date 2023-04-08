package seedu.calorietracker;

import seedu.storage.Storage;

import java.util.HashMap;

/**
 * Represents a dictionary that maps food to its calorie count.
 */
public class FoodDictionary {
    private final HashMap<String, Integer> foodCalories;

    public FoodDictionary() {
        foodCalories = new HashMap<>();
    }

    public FoodDictionary(HashMap<String, Integer> foodCalories) {
        this.foodCalories = foodCalories;
    }

    public FoodDictionary(Storage storage) {
        this.foodCalories = storage.readFoodDictionaryFile();
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
