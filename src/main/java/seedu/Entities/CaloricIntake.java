package seedu.Entities;

import java.util.ArrayList;

public class CaloricIntake {
    private ArrayList<Meal> DailyCalories;
    private float totalDailyCalories;

    public CaloricIntake(ArrayList<Meal> DailyCalories) {
        this.DailyCalories = DailyCalories;
        calculateTotalCalories();
    }

    public ArrayList<Meal> getDailyCalories(){
        return this.DailyCalories;
    }

    public void setDailyCalories(ArrayList<Meal> DailyCalories) {
        this.DailyCalories = DailyCalories;
    }

    public float getTotalDailyCalories() {
        return this.totalDailyCalories;
    }

    public void calculateTotalCalories() {
        for (Meal meal : DailyCalories ) {
            this.totalDailyCalories += meal.getTotalCalories();
        }
    }
}
