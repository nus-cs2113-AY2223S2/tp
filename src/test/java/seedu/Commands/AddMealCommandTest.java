package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.database.FoodStorage;
import seedu.database.MealStorage;
import seedu.entities.Food;
import seedu.entities.Meal;
import seedu.exceptions.InvalidArgumentsException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddMealCommandTest {

    private final FoodStorage foodStorage = new FoodStorage("./data/foodData.csv");
    private final MealStorage mealStorage = new MealStorage("./data/mealData.csv", foodStorage);
    private ArrayList<Food> foodList = new ArrayList<>();

    @Test
    void addMeal_singleMealAdded_expectListSizeIncrease() throws InvalidArgumentsException {
        int oldSize = mealStorage.getMealCount();
        foodList.add(foodStorage.getFoodById(2));
        String date = "1/1/2023";
        mealStorage.saveMeal(new Meal(foodList, date));
        int newSize = mealStorage.getMealCount();
        assertEquals(oldSize + 1, newSize);
    }
    @Test
    void parseInput_emptyInput_expectException() {

        String commandDescriptor = "";
        assertThrows(NumberFormatException.class,
                () -> Integer.parseInt(commandDescriptor));
    }
}
