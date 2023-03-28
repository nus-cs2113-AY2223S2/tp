package seedu.calorietracker;

import java.util.HashMap;

public class FoodList {
    private final HashMap<String, Integer> foodCalories;

    public FoodList() {
        foodCalories = new HashMap<>();
    }

    public void addFood(String name, int calories) {
        foodCalories.put(name, calories);
    }

    public int getCalories(String food) {
        return foodCalories.get(food);
    }

    public boolean contains(String food) {
        return foodCalories.containsKey(food);
    }
}
