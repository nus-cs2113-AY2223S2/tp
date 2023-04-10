package seedu.fst.storage;

import org.junit.jupiter.api.Test;
import seedu.fst.exceptions.FSTException;
import seedu.fst.food.Food;
import seedu.fst.food.FoodList;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.fst.food.FoodCategory.GRAIN;

public class FoodListEncoderTest {

    //TODO: Test for case where there is more than 1 food in foodList
    @Test
    void encodeFoodList_withExpiryDate_expectNameAndExpiry() throws FSTException {
        Food food = new Food("peanuts", "11/11/2023");
        FoodList fl = new FoodList();
        fl.addFood(food);
        ArrayList<String> encodedFoodLists = FoodListEncoder.encodeFoodList(fl);
        ArrayList<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("|n  peanuts|e  11/11/2023|q  0.0|u  unit|c  OTHERS");
        assertEquals(expectedOutput, encodedFoodLists);
    }

    @Test
    void encodeFoodList_withExpiryDateAndQuantity_expectNameExpiryAndCategory() throws FSTException {
        Food food = new Food("peanuts", "11/11/2023", GRAIN);
        FoodList fl = new FoodList();
        fl.addFood(food);
        ArrayList<String> encodedFoodLists = FoodListEncoder.encodeFoodList(fl);
        ArrayList<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("|n  peanuts|e  11/11/2023|q  0.0|u  unit|c  GRAIN");
        assertEquals(expectedOutput, encodedFoodLists);
    }

    @Test
    void encodeFoodList_withMultipleFood_expectMultipleFoodRecorded() throws FSTException {
        Food peanuts = new Food("peanuts", "11/11/2023");
        Food strawberry = new Food("strawberries", "31/05/2023");
        FoodList fl = new FoodList();
        fl.addFood(peanuts);
        fl.addFood(strawberry);
        ArrayList<String> encodedFoodLists = FoodListEncoder.encodeFoodList(fl);
        ArrayList<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("|n  peanuts|e  11/11/2023|q  0.0|u  unit|c  OTHERS");
        expectedOutput.add("|n  strawberries|e  31/05/2023|q  0.0|u  unit|c  OTHERS");
        assertEquals(expectedOutput, encodedFoodLists);
    }


    @Test
    void encodeFoodList_withExpiryDateAndQuantityWithUnits_expectNameExpiryAndQuantityWithUnits() throws FSTException {
        Food food = new Food("peanuts", "11/11/2023", 50.0, "g");
        FoodList fl = new FoodList();
        fl.addFood(food);
        FoodListEncoder fle = new FoodListEncoder();
        ArrayList<String> encodedFoodLists = fle.encodeFoodList(fl);
        ArrayList<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("|n  peanuts|e  11/11/2023|q  50.0|u  g|c  OTHERS");
        assertEquals(expectedOutput, encodedFoodLists);
    }
}

