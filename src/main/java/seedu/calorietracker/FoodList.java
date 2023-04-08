package seedu.calorietracker;

import java.util.ArrayList;

//@@author calebcjl
/**
 * Represents a list of food eaten in a single day.
 */
public class FoodList {
    private final ArrayList<Food> foods;
    private int totalCalories;

    public FoodList() {
        foods = new ArrayList<>();
        totalCalories = 0;
    }

    public void addFood(Food food) {
        foods.add(food);
        totalCalories += food.getCalories();
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public int getTotalCalories() {
        return totalCalories;
    }
}
