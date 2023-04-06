/*
package seedu.commands.calorietracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.calorietracker.Calories;
import seedu.calorietracker.CaloriesRecorder;

import java.util.Date;
import java.util.HashMap;

public class CaloriesRecorderTest {

    private CaloriesRecorder caloriesRecorder;
    private Calories calories;

    @BeforeEach
    public void setUp() {
        caloriesRecorder = new CaloriesRecorder();
        calories = new Calories(new Date());
    }

    @Test
    public void testAddFoodCalories() {
        calories.addFoodCalories("Apple", 50);
        calories.addFoodCalories("Banana", 100);
        caloriesRecorder.addFoodCalories(new Date(), calories);
        HashMap<Date, Calories> calorieMap = caloriesRecorder.getCalorieMap();
        Assertions.assertEquals(1, calorieMap.size());
        Assertions.assertTrue(calorieMap.containsKey(new Date()));
        Assertions.assertEquals(calories, calorieMap.get(new Date()));
    }

    @Test
    public void testGetCalories() {
        calories.addFoodCalories("Apple", 50);
        calories.addFoodCalories("Banana", 100);
        caloriesRecorder.setCalories(calories);
        Assertions.assertEquals(calories, caloriesRecorder.getCalories());
    }

}
*/
