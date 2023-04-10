package seedu.fst.commands;

import org.junit.jupiter.api.Test;
import seedu.fst.exceptions.FSTException;
import seedu.fst.food.Food;
import seedu.fst.food.FoodList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemoveCommandTest {

    @Test
    public void removeIndex_commandString_numberOfFoodLeft() throws FSTException {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("Milk", "25/03/2025"));
        foodList.addFood(new Food("Bread", "18/03/2025"));
        foodList.addFood(new Food("Cheese", "26/06/2025"));
        foodList.removeFood(2);
        int expectedOutput = 2;
        assertEquals(expectedOutput, foodList.getNumberOfFood());
    }
}
