package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.food.Food;
import seedu.duke.food.FoodList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindCommandTest {

    @Test
    public void termSearch_commandString_numberOfFoundFood() {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("Chicken egg", "25/04/2022"));
        foodList.addFood(new Food("Ostrich Egg  ", "26/04/2022"));
        foodList.addFood(new Food("Cow's Milk", "26/04/2022"));
        int expected = 2;
        assertEquals(expected, foodList.findFood("EGG ").getNumberOfFood());
    }

}
