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
    private static final String csvDelimiter = ",";

    public FoodStorage(String filePath) {
        super(filePath);
        foods = new ArrayList<Food>();
    }

    @Override
    public void load() throws IOException {
        String line = "";
        String[] foodLine;
        String foodType, name, store;
        int id, storeNumber;
        float calories;
        Food food;
        // parsing a CSV file into BufferedReader class constructor
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        while ((line = br.readLine()) != null)
        {
            foodLine = line.split(csvDelimiter); // use comma as separator
            id = Integer.parseInt(foodLine[0]);
            foodType = foodLine[1];
            name = foodLine[2];
            store = foodLine[3];
            storeNumber = Integer.parseInt(foodLine[4]);
            calories = Float.parseFloat(foodLine[5]);
            food = null;
            
            if (foodType.equals(FoodTypes.DISH.toString())) {
                food = new Dish(id, name, store, storeNumber, calories);
            } else if (foodType.equals(FoodTypes.SIDE.toString())) {
                food = new Side(id, name, store, storeNumber, calories);
            } else if (foodType.equals(FoodTypes.INGREDIENT.toString())) {
                food = new Ingredient(id, name, store, storeNumber, calories);
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
