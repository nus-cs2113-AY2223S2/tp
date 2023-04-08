package seedu.calorietracker;

import java.util.ArrayList;

/**
 * Represents a list of food eaten in a single day.
 */
public class FoodList {
    ArrayList<Food> foods;

    public FoodList() {
        foods = new ArrayList<>();
    }

    public void addFood(Food food) {
        foods.add(food);
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }
}
