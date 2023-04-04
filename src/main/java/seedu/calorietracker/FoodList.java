package seedu.calorietracker;


import java.util.HashMap;

public class FoodList {
    private HashMap<String, Integer> foodCalories;

    public FoodList() {
        foodCalories = new HashMap<>();
    }

    public void addFood(String name, int calories) {
        foodCalories.put(name, calories);
    }

    public int getCalories(String food) {
        return foodCalories.get(food);
    }

    public HashMap<String, Integer> getFoodCalories() {
        return foodCalories;
    }

    public boolean contains(String food) {
        return foodCalories.containsKey(food);
    }
}
