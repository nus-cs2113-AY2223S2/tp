package seedu.calorietracker;

import java.util.Date;
import java.util.HashMap;

public class CaloriesRecorder {
    private HashMap<Date, Calories> calorieMap;
    private Calories calories;
    public CaloriesRecorder() {
        calorieMap = new HashMap<>();
    }

    public HashMap<Date, Calories> getCalorieMap() {
        return calorieMap;
    }

    public void addFoodCalories(Date date, Calories calories){
        calorieMap.put(date, calories);
        this.calories = calories;
    }

    public void setCalories(Calories calories) {
        this.calories = calories;
    }

    public Calories getCalories() {
        return calories;
    }

}
