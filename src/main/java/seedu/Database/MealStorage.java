package seedu.Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import seedu.Entities.Meal;
import seedu.Entities.Food;

public class MealStorage extends Storage implements FileReadable, FileWritable {
    private ArrayList<Meal> meals;
    private FoodStorage foodStorage;
    private static final String csvDelimiter = ",";
    private static final String foodsDelimiter = "-";

    public MealStorage(String filePath, FoodStorage foodStorage) {
        super(filePath);
        this.foodStorage = foodStorage;
        meals = new ArrayList<Meal>();
    }

    @Override
    public void write() throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(filePath));
        String[] header = { "Date", "Foods" };
        writer.writeNext(header);
        for (Meal meal : meals)
        {
            writer.writeNext(meal.toWriteFormat(foodsDelimiter));
        }
        writer.close();
    }


    @Override
    public void load() throws IOException {
        String line = "";
        String[] mealLine;
        String date;
        String[] foodIndexes;
        ArrayList<Food> allFoods = foodStorage.getFoods();
        ArrayList<Food> foods;

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        while ((line = br.readLine()) != null)
        {
            mealLine = line.split(csvDelimiter);
            date = mealLine[0];
            foodIndexes = mealLine[1].split(foodsDelimiter);
            foods = new ArrayList<Food>();
            for (String foodIndex : foodIndexes)
            {
                foods.add(allFoods.get(Integer.parseInt(foodIndex)))
            }
            meals.add(new Meal(foods, date));
        }
    }

    public void saveMeal(Meal meal) {
        meals.add(meal);
        try {
            this.write();
            System.out.println("Meal was successfully added!");
        } catch (IOException e) {
            System.out.println("Error saving meal to storage!");
        }
    }
}