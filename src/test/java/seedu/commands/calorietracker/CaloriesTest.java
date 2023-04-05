/*
package seedu.commands.calorietracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.calorietracker.Calories;

import java.util.Date;
import java.util.HashMap;

public class CaloriesTest {

    private Calories calories;

    @BeforeEach
    public void setUp() {
        calories = new Calories(new Date());
    }

    @Test
    public void testAddFoodCalories() {
        calories.addFoodCalories("Apple", 50);
        HashMap<String, Integer> singleFoodCalories = calories.getSingleFoodCalories();
        Assertions.assertEquals(1, singleFoodCalories.size());
        Assertions.assertTrue(singleFoodCalories.containsKey("Apple"));
        Assertions.assertEquals(50, singleFoodCalories.get("Apple"));

        calories.addFoodCalories("Apple", 100);
        Assertions.assertEquals(1, singleFoodCalories.size());
        Assertions.assertEquals(150, singleFoodCalories.get("Apple"));

        calories.addFoodCalories("Banana", 200);
        Assertions.assertEquals(2, singleFoodCalories.size());
        Assertions.assertTrue(singleFoodCalories.containsKey("Banana"));
        Assertions.assertEquals(200, singleFoodCalories.get("Banana"));
    }

    @Test
    public void testCheckFood() {
        calories.addFoodCalories("Apple", 50);
        Assertions.assertTrue(calories.checkFood("Apple"));
        Assertions.assertFalse(calories.checkFood("Banana"));
    }

    @Test
    public void testAddToCurrentFoodCalories() {
        calories.addFoodCalories("Apple", 50);
        calories.addToCurrentFoodCalories("Apple", 100);
        HashMap<String, Integer> singleFoodCalories = calories.getSingleFoodCalories();
        Assertions.assertEquals(1, singleFoodCalories.size());
        Assertions.assertEquals(150, singleFoodCalories.get("Apple"));

        calories.addToCurrentFoodCalories("Banana", 200);
        Assertions.assertEquals(1, singleFoodCalories.size());
        Assertions.assertFalse(singleFoodCalories.containsKey("Banana"));
    }
}

*/
