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
import static seedu.fst.food.FoodCategory.FRUIT;

//@@author DavidVin357
public class UpdateCommandTest {
    private String getExpectedFeedback(int foodIndex, FoodList foodList) throws FSTException {
        return "Updated food item successfully! \n" + foodList.getFood(foodIndex);
    }

    String updateWithException(FoodList foodList, String arguments) {
        try {
            return new UpdateCommand(arguments).execute(foodList).feedbackToUser;
        } catch (FSTException e){
            return e.getMessage();
        }
    }
    @Test
    void updateFood_withUnitAndZeroQuantity_exception() throws FSTException {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("KG Eggs", "25/04/2024", 0.0, "kg", DAIRY));
        foodList.addFood(new Food("Unit egg", "25/04/2024", 1.2, "unit", OTHERS));
        foodList.addFood(new Food("KG Carrot", "25/04/2024", 4.2, "kg", VEGETABLE));
        foodList.addFood(new Food("ML Water", "25/04/2024", 13.0, "ml", BEVERAGE));

        assertEquals("Please set the quantity to change a unit", updateWithException(foodList, "1 --u kg"));
    }

    @Test
    void updateFood_withQuantityFlag_servingsUnit() throws FSTException {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("KG Eggs", "25/04/2024", 0.0, "kg", DAIRY));
        foodList.addFood(new Food("Unit egg", "25/04/2024", 1.2, "unit", OTHERS));
        foodList.addFood(new Food("KG Carrot", "25/04/2024", 4.2, "kg", VEGETABLE));
        foodList.addFood(new Food("ML Water", "25/04/2024", 13.0, "ml", BEVERAGE));

        updateWithException(foodList, "2 --u serving");
        assertEquals("servings", foodList.getFood(1).getUnit());
    }

    @Test
    void updateFood_withCategoryFlag_othersCategory() throws FSTException {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("KG Eggs", "25/04/2024", 0.0, "kg", DAIRY));
        foodList.addFood(new Food("Unit egg", "25/04/2024", 1.2, "unit", OTHERS));
        foodList.addFood(new Food("KG Carrot", "25/04/2024", 4.2, "kg", VEGETABLE));
        foodList.addFood(new Food("ML Water", "25/04/2024", 13.0, "ml", BEVERAGE));

        updateWithException(foodList, "3 --c custom category");
        assertEquals(OTHERS, foodList.getFood(2).getCategory());
    }

    @Test
    void updateFood_withMultiWordNameFlag_customName() throws FSTException {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("KG Eggs", "25/04/2024", 0.0, "kg", DAIRY));
        foodList.addFood(new Food("Unit egg", "25/04/2024", 1.2, "unit", OTHERS));
        foodList.addFood(new Food("KG Carrot", "25/04/2024", 4.2, "kg", VEGETABLE));
        foodList.addFood(new Food("ML Water", "25/04/2024", 13.0, "ml", BEVERAGE));
        updateWithException(foodList, "1 --n frozen ratatouille");
        assertEquals("frozen ratatouille", foodList.getFood(0).getName());
    }

    @Test
    void updateFood_withAllFlags_foodAttributes() throws FSTException {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("KG Eggs", "25/04/2024", 0.0, "kg", DAIRY));
        foodList.addFood(new Food("Unit egg", "25/04/2024", 1.2, "unit", OTHERS));
        foodList.addFood(new Food("KG Carrot", "25/04/2024", 4.2, "kg", VEGETABLE));
        foodList.addFood(new Food("ML Water", "25/04/2024", 13.0, "ml", BEVERAGE));
        updateWithException(foodList, "1 --n sour pineapple   --c fruit --e 07/07/2024 --q 12.68 --u kg");

        assertEquals("sour pineapple", foodList.getFood(0).getName());
        assertEquals(FRUIT, foodList.getFood(0).getCategory());
        assertEquals("07/07/2024", foodList.getFood(0).getExpiryDate());
        assertEquals(12.68, foodList.getFood(0).getQuantity());
        assertEquals("kg", foodList.getFood(0).getUnit());
    }
}
