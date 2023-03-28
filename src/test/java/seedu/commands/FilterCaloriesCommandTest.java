package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.constants.DateConstants;
import seedu.entities.Food;
import seedu.exceptions.LifeTrackerException;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FilterCaloriesCommandTest {

    private final FoodStorage foodStorage = new FoodStorage();
    private final MealStorage mealStorage = new MealStorage("./data/mealData.csv", foodStorage);
    private final DateTimeFormatter dtf = DateConstants.DATABASE_DTF;
    private ArrayList<Food> foodList = new ArrayList<>();

    @Test
    void filterCalories_validLowerAndUpperLimit_expectNoExceptions() throws LifeTrackerException {
        mealStorage.resetStorage();
        int foodsInRange = 0;
        int caloriesLowerLimit = 500;
        int caloriesUpperLimit = 1000;
        for (Food food: foodStorage.getFoods()){
            if (food.getCalories() > caloriesLowerLimit && food.getCalories() < caloriesUpperLimit){
                foodsInRange += 1;
            }
        }
        List<Food> caloriesFilteredFoods = foodStorage.getFoodsByCalories(caloriesLowerLimit, caloriesUpperLimit);
        assertTrue(foodsInRange == caloriesFilteredFoods.size());
    }

    @Test
    void filterCalories_invalidLowerAndUpperLimit_expectException() throws LifeTrackerException {
        mealStorage.resetStorage();
        int caloriesLowerLimit = 1000;
        int caloriesUpperLimit = 500;
        LifeTrackerException thrown = assertThrows(LifeTrackerException.class, () -> {
            foodStorage.getFoodsByCalories(caloriesLowerLimit,caloriesUpperLimit);
        });
        String expectedErrorMessage = "Please enter valid lower and upper calorie limits!";
        assertEquals(expectedErrorMessage, thrown.getMessage());
    }
}
