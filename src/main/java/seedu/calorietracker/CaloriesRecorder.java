package seedu.calorietracker;

import java.util.Date;
import java.util.HashMap;

public class CaloriesRecorder {
    private HashMap<Date, Calories> calorieMap;
    private Calories calories = new Calories();

    public CaloriesRecorder() {
        calorieMap = new HashMap<>();
    }

    public HashMap<Date, Calories> getCalorieMap() {
        return calorieMap;
    }

    public void addFoodCalories(Date date, Calories calories){
        calorieMap.put(date, calories);
        //setDate(date);
        //setCalories(calories);
        this.calories = calories;
    }

    public void setCalories(Calories calories) {
        this.calories = calories;
    }

    public void setDate(Date date) {
    }
    public Calories getCalories() {
        return calories;
    }

}
