package seedu.entities;

import java.util.ArrayList;

public class CaloricIntake {
    private ArrayList<Meal> dailyCalories;
    private float totalDailyCalories;

    public CaloricIntake(ArrayList<Meal> dailyCalories) {
        this.dailyCalories = dailyCalories;
        calculateTotalCalories();
    }

    public ArrayList<Meal> getDailyCalories(){
        return this.dailyCalories;
    }

    public void setDailyCalories(ArrayList<Meal> dailyCalories) {
        this.dailyCalories = dailyCalories;
    }

    public float getTotalDailyCalories() {
        return this.totalDailyCalories;
    }

    public void calculateTotalCalories() {
        for (Meal meal : dailyCalories) {
            this.totalDailyCalories += meal.getTotalCalories();
        }
    }
}
