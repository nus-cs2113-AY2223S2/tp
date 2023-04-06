package seedu.commands.caloriecommands;

import seedu.calorietracker.Calories;
import seedu.commands.Command;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CaloriesCommand extends Command {
    protected static boolean isDateEntered;
    protected HashMap<Date, Calories> calorieMap;
    protected HashMap<String, Integer> foodCalories;




    // protected Calories calories;


    public CaloriesCommand() {
    }

    public static void setDateEntered(boolean dateEntered) {
        isDateEntered = dateEntered;
    }

    public Map<Date, Calories> getCalorieMap() {
        return calorieMap;
    }

    public HashMap<String, Integer> getFoodCalories() {
        return foodCalories;
    }

    public void setFoodCalories(HashMap<String, Integer> foodCalories) {
        this.foodCalories = foodCalories;
    }

    public String execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

}
