package seedu.calorietracker;

import seedu.exceptions.InvalidSyntaxException;
import seedu.parser.DateFormatter;
import seedu.storage.Storage;
import seedu.ui.Ui;

import java.util.Date;
import java.util.HashMap;

import static seedu.commands.caloriecommands.AddCalorieCommand.CALORIES_NOT_GIVEN;

//@@author calebcjl
/**
 * Represents a calorie tracker.
 */
public class CalorieTracker {
    private final HashMap<Date, FoodList> dailyFoodConsumption;
    private final FoodDictionary foodDictionary;


    //@@author ZIZI-czh
    /**
     * Constructs a new CalorieTracker object with the given storage and food dictionary. The
     * initial daily food consumption records are read from the storage, and the food dictionary
     * is used to look up food items by their names.
     *
     * @param storage the storage object to use for reading and writing the daily food consumption
     *                records
     * @param foodDictionary the food dictionary object to use for looking up food items by their names
     */
    public CalorieTracker(Storage storage, FoodDictionary foodDictionary) {
        dailyFoodConsumption = storage.readCalorieTrackerFile();
        this.foodDictionary = foodDictionary;
    }

    //@@author ZIZI-czh
    /**
     * Returns a FoodList representing the list of foods consumed on the specified date. If no
     * such list exists in the daily food consumption records, a new, empty list is created and
     * added to the records for that date.
     *
     * @param date the date for which to retrieve the list of consumed foods
     * @return a FoodList object representing the list of foods consumed on the specified date
     */
    public FoodList getFoodList(Date date) {
        if (!dailyFoodConsumption.containsKey(date)) {
            FoodList foodList = new FoodList();
            dailyFoodConsumption.put(date, foodList);
        }
        return dailyFoodConsumption.get(date);
    }

    //@@author ZIZI-czh

    public HashMap<Date, FoodList> getDailyFoodConsumption() {
        return dailyFoodConsumption;
    }

    /**
     * Add calorie consumption to CalorieTracker.
     *
     * @param date Date of consumption.
     * @param foodName Name of food.
     * @param foodCalories Calories of food.
     * @return Output string.
     * @throws InvalidSyntaxException If invalid syntax.
     */
    public String addCalories(Date date, String foodName, int foodCalories) throws InvalidSyntaxException {
        FoodList foodList = getFoodList(date);
        Food foodToAdd;
        if (foodCalories == CALORIES_NOT_GIVEN) {
            if (foodDictionary.contains(foodName)) {
                foodToAdd = new Food(foodName, foodDictionary.getFoodCalories().get(foodName));
            } else {
                throw new InvalidSyntaxException("food calories");
            }
        } else {
            foodToAdd = new Food(foodName, foodCalories);
            foodDictionary.addFood(foodName, foodCalories);
        }
        foodList.addFood(foodToAdd);

        return "Added " + foodName + "(" + foodToAdd.getCalories() + " kcal) to "
                + DateFormatter.dateToString(date) + "." + System.lineSeparator() + Ui.line();
    }
}
