package seedu.Entities;

import java.util.ArrayList;

public class Meal {
    private ArrayList<Food> foods;
    private String date;
    private float totalCalories;

    public Meal(ArrayList<Food> foods, String date) {
        this.foods = foods;
        this.date = date;
        calculateTotalCalories();
    }

    public ArrayList<Food> getFoods() {
        return this.foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getTotalCalories() {
        return this.totalCalories;
    }

    public void calculateTotalCalories() {
        for (Food food : foods) {
            this.totalCalories += food.getCalories();
        }
    }
}
