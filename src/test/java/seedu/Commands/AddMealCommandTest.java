package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.database.FoodStorage;
import seedu.database.MealStorage;
import seedu.definitions.MealTypes;
import seedu.entities.Food;
import seedu.entities.Meal;
import seedu.exceptions.InvalidArgumentsException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AddMealCommandTest {
    private final FoodStorage foodStorage = new FoodStorage();
    private final MealStorage mealStorage = new MealStorage("./data/mealData.csv", foodStorage);
    private final DateTimeFormatter dtf = mealStorage.getDateTimeFormatter();
    private ArrayList<Food> foodList = new ArrayList<>();

    @Test
    void addMeal_singleMealAdded_expectListSizeIncrease() throws InvalidArgumentsException {
        mealStorage.resetStorage();
        int oldSize = mealStorage.getMealCount();
        foodList.add(foodStorage.getFoodById(2));
        LocalDate date = LocalDate.parse("1/1/2023", dtf);
        mealStorage.saveMeal(new Meal(foodList, date, MealTypes.BREAKFAST));
        int newSize = mealStorage.getMealCount();
        assertEquals(oldSize + 1, newSize);
    }

    @Test
    void addTwoMeals_twoDifferentMealsAddedAtDifferentDates_expectSortedMeals() {
        mealStorage.resetStorage();
        LocalDate date;
        foodList.add(foodStorage.getFoodById(2));
        date = LocalDate.parse("31/12/2023", dtf);
        mealStorage.saveMeal(new Meal(foodList, date, MealTypes.BREAKFAST));
        date = LocalDate.parse("1/11/2023", dtf);
        mealStorage.saveMeal(new Meal(foodList, date, MealTypes.BREAKFAST));
        Meal meal0 = mealStorage.getMealById(0);
        Meal meal1 = mealStorage.getMealById(1);
        assertFalse(meal0.compareTo(meal1) > 0, "Array is not sorted!");
    }

    @Test
    void addTwoMeals_twoDifferentMealsAddedAtDifferentMealTypes_expectSortedMeals() {
        mealStorage.resetStorage();
        LocalDate date;
        foodList.add(foodStorage.getFoodById(2));
        date = LocalDate.parse("31/12/2023", dtf);
        mealStorage.saveMeal(new Meal(foodList, date, MealTypes.LUNCH));
        date = LocalDate.parse("31/12/2023", dtf);
        mealStorage.saveMeal(new Meal(foodList, date, MealTypes.BREAKFAST));
        Meal meal0 = mealStorage.getMealById(0);
        Meal meal1 = mealStorage.getMealById(1);
        assertFalse(meal0.compareTo(meal1) > 0, "Array is not sorted!");
    }

    @Test
    void parseInput_emptyInput_expectException() {

        String commandDescriptor = "";
        assertThrows(NumberFormatException.class,
                () -> Integer.parseInt(commandDescriptor));
    }
}
