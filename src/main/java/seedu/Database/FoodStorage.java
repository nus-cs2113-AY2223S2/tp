package seedu.Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import seedu.Definitions.FoodTypes;
import seedu.Entities.Dish;
import seedu.Entities.Food;
import seedu.Entities.Ingredient;
import seedu.Entities.Side;

public class FoodStorage extends Storage implements FileReadable {
    private ArrayList<Food> foods;

    public FoodStorage(String filePath) {
        super(filePath);
        foods = new ArrayList<Food>();
    }

    @Override
    public void load() throws IOException {
        String line = "", splitBy = ",";
        String foodType, name, store;
        int storeNumber;
        float calories;
        Food food;
        // parsing a CSV file into BufferedReader class constructor
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        while ((line = br.readLine()) != null) // returns a Boolean value
        {
            String[] foodLine = line.split(splitBy); // use comma as separator
            foodType = foodLine[0];
            name = foodLine[1];
            store = foodLine[2];
            storeNumber = Integer.parseInt(foodLine[3]);
            calories = Float.parseFloat(foodLine[4]);
            food = null;
            
            if (foodType.equals(FoodTypes.DISH.toString())) {
                food = new Dish(name, store, storeNumber, calories);
            } else if (foodType.equals(FoodTypes.SIDE.toString())) {
                food = new Side(name, store, storeNumber, calories);
            } else if (foodType.equals(FoodTypes.INGREDIENT.toString())) {
                food = new Ingredient(name, store, storeNumber, calories);
            }

            if (food != null) {
                foods.add(food);
            }
        }
        br.close();
    }

    public ArrayList<Food> getFoods() {
        return this.foods;
    }
}
