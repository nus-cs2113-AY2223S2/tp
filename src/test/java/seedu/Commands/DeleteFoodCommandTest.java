package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.database.FoodStorage;
import seedu.database.MealStorage;
import seedu.exceptions.InvalidArgumentsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteFoodCommandTest {
    private final FoodStorage foodStorage = new FoodStorage("./data/foodData.csv");
    private final MealStorage mealStorage = new MealStorage("./data/mealData.csv", foodStorage);

    @Test
    void deleteMeal_singleMealDeleted_expectListSizeDecrease() throws InvalidArgumentsException {
        int oldSize = mealStorage.getMealCount();
        if (oldSize >= 0) {
            mealStorage.deleteMeal(oldSize - 1);
            int newSize = mealStorage.getMealCount();
            assertEquals(oldSize - 1, newSize);
        } else {
            assertEquals(oldSize, mealStorage.getMealCount());
        }
    }
    @Test
    void parseInput_emptyInput_expectException() {
        String commandDescriptor = "";
        assertThrows(NumberFormatException.class,
                () -> Integer.parseInt(commandDescriptor));
    }
}
