package seedu.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.constants.DateConstants;
import seedu.definitions.MealTypes;
import seedu.entities.Food;
import seedu.entities.Meal;

public class MealStorageTest {
    private static final String FILE_PATH = "./data/mealData.csv";
    private final FoodStorage foodStorage = new FoodStorage();

    @Nested
    @DisplayName("Test Read Write Functionalities")
    class ReadWriteTest {
        private final MealStorage mealStorage = new MealStorage(FILE_PATH, foodStorage);
        private final DateTimeFormatter dtf = DateConstants.DATABASE_DTF;
        private ArrayList<Food> foodList = new ArrayList<Food>();

        @Test
        public void addMeal_singleMealAdded_expectNoException() {
            assertFalse(foodStorage.getFoodsCount() == 0, "Food Storage is not empty :)");
            foodList.add(foodStorage.getFoodById(0));
            foodList.add(foodStorage.getFoodById(1));
            foodList.add(foodStorage.getFoodById(2));
            LocalDate date = LocalDate.parse("1/1/2023", dtf);
            assertDoesNotThrow(() -> mealStorage.saveMeal(new Meal(foodList, date, MealTypes.BREAKFAST)));
        }

        @Test
        public void retrieveMeal_singleMealAlreadyAdded_expectNoException() {
            addMeal_singleMealAdded_expectNoException();
            assertDoesNotThrow(() -> mealStorage.getMealById(0));
        }

        @Test
        public void deleteMeal_singleMealAlreadyAdded_expectNoException() {
            addMeal_singleMealAdded_expectNoException();
            assertDoesNotThrow(() -> mealStorage.deleteMeal(0));
        }
    }
}
