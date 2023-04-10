package seedu.fst.commands;

import org.junit.jupiter.api.Test;
import seedu.fst.exceptions.FSTException;
import seedu.fst.food.Food;
import seedu.fst.food.FoodList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.fst.food.FoodCategory.DAIRY;
import static seedu.fst.food.FoodCategory.VEGETABLE;
import static seedu.fst.food.FoodCategory.BEVERAGE;
import static seedu.fst.food.FoodCategory.OTHERS;

//@@author DavidVin357
public class FindCommandTest {
    private String getExpectedFeedback(int expectedNumber) {
        final String FOUND_FOOD = "Found " + expectedNumber + " of food items";
        final String NOT_FOUND = "No food found for such query";
        if (expectedNumber > 0) {
            return FOUND_FOOD;
        }
        return NOT_FOUND;
    }
    @Test
    public void termSearch_withoutFlags_numberOfFoundFood() throws FSTException {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("Chicken egg", "25/04/2025"));
        foodList.addFood(new Food("Ostrich Egg  ", "26/04/2025"));
        foodList.addFood(new Food("Cow's Milk", "27/04/2025"));

        assertEquals(getExpectedFeedback(2), new FindCommand("EGG ").execute(foodList).feedbackToUser);
    }

    @Test void termSearch_withFreshFlag_numberOfFoundFood() throws FSTException {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("Expired egg", "25/04/2024"));
        foodList.addFood(new Food("Good Egg  ", "26/04/2025"));
        foodList.addFood(new Food("Expired Milk", "27/04/2024"));
        foodList.addFood(new Food("Good Milk", "27/04/2025"));
        assertEquals(getExpectedFeedback(2), new FindCommand("EGG --fresh").execute(foodList).feedbackToUser);
    }

    @Test void termSearch_withQuantityFlag_numberOfFoundFood() throws FSTException {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("KG Eggs", "25/04/2024", 3.4, "kg", DAIRY));
        foodList.addFood(new Food("Unit egg", "25/04/2024", 1.2, "unit", OTHERS));
        foodList.addFood(new Food("KG Carrot", "25/04/2024", 4.2, "kg", VEGETABLE));
        foodList.addFood(new Food("ML Water", "25/04/2024", 13.0, "ml", BEVERAGE));
        assertEquals(getExpectedFeedback(1), new FindCommand("water   --q 13").execute(foodList).feedbackToUser);
    }

    @Test void termSearch_withUnitFlag_numberOfFoundFood() throws FSTException {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("KG Eggs", "25/04/2024", 3.4, "kg", DAIRY));
        foodList.addFood(new Food("Unit egg", "25/04/2024", 1.2, "unit", OTHERS));
        foodList.addFood(new Food("KG Carrot", "25/04/2024", 4.2, "kg", VEGETABLE));
        foodList.addFood(new Food("ML Water", "25/04/2024", 13.0, "ml", BEVERAGE));
        assertEquals(getExpectedFeedback(1), new FindCommand("EGG --u kg").execute(foodList).feedbackToUser);
    }

    @Test void termSearch_withCategoryFlag_numberOfFoundFood() throws FSTException {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("KG Eggs", "25/04/2024", 3.4, "kg", DAIRY));
        foodList.addFood(new Food("Unit egg", "25/04/2024", 1.2, "unit", OTHERS));
        foodList.addFood(new Food("KG Carrot", "25/04/2024", 4.2, "kg", VEGETABLE));
        foodList.addFood(new Food("ML Water", "25/04/2024", 13.0, "ml", BEVERAGE));
        assertEquals(getExpectedFeedback(0), new FindCommand("EGG --c vegetable").execute(foodList).feedbackToUser);
    }

    @Test void termSearch_withFreshAndExpiredFlags_numberOfFoundFood() throws FSTException {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("KG Eggs", "25/04/2024", 3.4, "kg", DAIRY));
        foodList.addFood(new Food("Unit egg", "25/04/2024", 1.2, "unit", OTHERS));
        foodList.addFood(new Food("KG Carrot", "25/04/2024", 4.2, "kg", VEGETABLE));
        foodList.addFood(new Food("ML Water", "25/04/2024", 13.0, "ml", BEVERAGE));
        assertEquals(getExpectedFeedback(0), new FindCommand("EGG --fresh --expired").execute(foodList).feedbackToUser);
    }

    @Test void termSearch_withAllFlags_numberOfFoundFood() throws FSTException {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("KG Eggs", "25/04/2024", 3.4, "kg", DAIRY));
        foodList.addFood(new Food("Unit egg", "25/04/2024", 1.2, "unit", OTHERS));
        foodList.addFood(new Food("KG Carrot", "25/04/2024", 4.2, "kg", VEGETABLE));
        foodList.addFood(new Food("ML Water", "25/04/2024", 13.0, "ml", BEVERAGE));
        foodList.addFood(new Food("L Sprite", "25/04/2024", 3.34, "l", BEVERAGE));
        foodList.addFood(new Food("L Orange Water", "25/04/2024", 13.0, "ml", BEVERAGE));
        assertEquals(getExpectedFeedback(2),
                new FindCommand("water --fresh --c beverage --q 13 --u ml").execute(foodList).feedbackToUser);
    }

}
