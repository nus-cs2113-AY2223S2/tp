package seedu.calorietracker;

import java.util.Date;
import java.util.HashMap;

public class Calories {


    private HashMap<String, Integer> singleFoodCalories = new HashMap<>();

    private String name;
    private int calories;

    private Date date;
    public Calories() {

    }
    public Calories(Date date) {
        this.date = date;
    }

    public void addFoodCalories(String name, int calories) {
        singleFoodCalories.put(name, calories);
        this.name = name;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }



    public boolean checkFood(String foodName) {
        return singleFoodCalories.containsKey(foodName);
    }

    public void addToCurrentFoodCalories(String foodName, int newCalories) {
        int pastValue = singleFoodCalories.get(foodName);
        int newValue = pastValue + newCalories;
        singleFoodCalories.put(foodName, newValue);
    }

    public HashMap<String, Integer> getSingleFoodCalories() {
        return singleFoodCalories;
    }
}

