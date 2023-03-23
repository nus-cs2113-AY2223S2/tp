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

    public FoodStorage() {
        foods = new ArrayList<Food>();
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
        float protein;
        float totalFat;
        float saturatedFat;
        float dietaryFibre;
        float carbohydrates;
        float sugar;
        float sodium;
        Food food;
        
        String[] foodDataList = FoodData.getFoodData();
        
        for (int i = 1; i < foodDataList.length; i++) {

            line = foodDataList[i];
            foodLine = line.split(csvDelimiter); // use comma as separator
            double[] individualFoodData = new double[13];

            id = Integer.parseInt(foodLine[0]);
            foodType = foodLine[1];
            name = foodLine[2];
            store = foodLine[3];
            storeNumber = Integer.parseInt(foodLine[4]);

            for (int j = 4; j < 13; j++) {
                String valueStr = (j < foodLine.length) ? foodLine[j] : null;
                float value = (valueStr != null && !valueStr.isEmpty()) ? Float.parseFloat(valueStr) : 0;
                individualFoodData[j] = value;
            }

            calories = (float) individualFoodData[5];
            protein = (float) individualFoodData[6];
            totalFat = (float) individualFoodData[7];
            saturatedFat = (float) individualFoodData[8];
            dietaryFibre = (float) individualFoodData[9];
            carbohydrates = (float) individualFoodData[10];
            sugar = (float) individualFoodData[11];
            sodium = (float) individualFoodData[12];

            food = null;
            
            if (foodType.equals(FoodTypes.DISH.toString())) {
                food = new Dish(id, name, store, storeNumber, calories, protein, totalFat, saturatedFat,dietaryFibre,
                        carbohydrates, sugar, sodium);
            } else if (foodType.equals(FoodTypes.SIDE.toString())) {
                food = new Side(id, name, store, storeNumber, calories, protein, totalFat, saturatedFat,dietaryFibre,
                        carbohydrates, sugar, sodium);
            } else if (foodType.equals(FoodTypes.INGREDIENT.toString())) {
                food = new Ingredient(id, name, store, storeNumber, calories, protein, totalFat, saturatedFat,
                        dietaryFibre, carbohydrates, sugar, sodium);
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

    public List<Food> getFoodsByCalories(float caloriesLowerLimit, float caloriesUpperLimit) {
        List<Food> caloriesFilteredFoods = foods.stream()
                .filter(f -> f.getCalories() >= caloriesLowerLimit && f.getCalories() <= caloriesUpperLimit)
                .collect(Collectors.toList());
        return caloriesFilteredFoods;
    }
}
