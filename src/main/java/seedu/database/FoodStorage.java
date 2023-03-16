package seedu.database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.definitions.FoodTypes;
import seedu.entities.Dish;
import seedu.entities.Food;
import seedu.entities.Ingredient;
import seedu.entities.Side;

public class FoodStorage extends Storage implements FileReadable {
    private static final String csvDelimiter = ",";
    private ArrayList<Food> foods;
    private FoodData foodData;

    public FoodStorage() {
        foods = new ArrayList<Food>();
        foodData = new FoodData();
        try {
            this.load();
        } catch (IOException e) {
            System.out.println("Error loading Food Storage");
        }
    }

    @Override
    public void load() throws IOException {
        String line = "";
        String[] foodLine;
        String foodType;
        String name;
        String store;
        int id;
        int storeNumber;
        float calories;
        Food food;
        
        String[] foodDataList = foodData.getFoodData();
        
        for (int i = 1; i < foodDataList.length; i++) {
            line = foodDataList[i];
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
    }

    public int getFoodsCount() {
        return this.foods.size();
    }

    public ArrayList<Food> getFoods() {
        return this.foods;
    }

    public Food getFoodById(int id) {
        return this.foods.get(id);
    }

    public List<Food> getFoodsByName(String name) {
        List<Food> filteredFoods = foods.stream()
                .filter(f -> f.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        return filteredFoods;
    }
}
