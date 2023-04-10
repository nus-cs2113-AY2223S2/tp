package seedu.entities;

import java.util.List;

public class CaloricIntake {
    private List<Meal> dailyCalories;
    private float totalDailyCalories;

    public CaloricIntake(List<Meal> dailyCalories) {
        this.dailyCalories = dailyCalories;
        calculateTotalCalories();
    }

    public List<Meal> getDailyCalories() {
        return this.dailyCalories;
    }

    public void setDailyCalories(List<Meal> dailyCalories) {
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
