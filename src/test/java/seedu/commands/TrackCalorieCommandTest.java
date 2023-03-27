package seedu.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.constants.DateConstants;
import seedu.definitions.MealTypes;
import seedu.entities.Food;
import seedu.entities.Meal;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;

public class TrackCalorieCommandTest {
    private final FoodStorage foodStorage = new FoodStorage();
    private final MealStorage mealStorage = new MealStorage("./data/mealData.csv", foodStorage);
    private final DateTimeFormatter dtf = DateConstants.DATABASE_DTF;
    private ArrayList<Food> foodList;

    @Test
    public void trackCalories_singleMealAdded_expectNoException() {
        foodList = new ArrayList<Food>();
        mealStorage.resetStorage();
        foodList.add(foodStorage.getFoodById(2));
        LocalDate date = LocalDate.parse("1/1/2023", dtf);
        mealStorage.saveMeal(new Meal(foodList, date, MealTypes.BREAKFAST));
        assertDoesNotThrow(() -> {
            Command command = new TrackCalorieCommand();
            command.execute(null, foodStorage, mealStorage, null, null);
        });
    }
}
