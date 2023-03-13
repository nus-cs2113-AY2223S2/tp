package seedu.Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import seedu.Entities.Meal;
import seedu.Entities.Food;
import com.opencsv.CSVWriter;

public class MealStorage extends Storage implements FileReadable, FileWritable {
    private static final String csvDelimiter = ",";
    private static final String foodsDelimiter = "-";
    private ArrayList<Meal> meals;
    private FoodStorage foodStorage;

    public MealStorage(String filePath, FoodStorage foodStorage) {
        super(filePath);
        this.foodStorage = foodStorage;
        meals = new ArrayList<Meal>();
        try {
            this.load();
        } catch (IOException e) {
            System.out.println("Error loading Meal Storage");
        }
    }

    @Override
    public void write() throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(filePath),
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.RFC4180_LINE_END);
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
        ArrayList<Food> foods;

        BufferedReader br = new BufferedReader(new FileReader(filePath));

        // Skip Line 1 (header)
        br.readLine();

        while ((line = br.readLine()) != null) {
            mealLine = line.split(csvDelimiter);
            date = mealLine[0];
            foodIndexes = mealLine[1].split(foodsDelimiter);
            foods = new ArrayList<Food>();
            for (String foodIndex : foodIndexes) {
                foods.add(foodStorage.getFoodById(Integer.parseInt(foodIndex)));
            }
            meals.add(new Meal(foods, date));
        }

        br.close();
    }

    public void saveMeal(Meal meal) {
        meals.add(meal);
        try {
            this.write();
            System.out.println("Meal was successfully added!");
        } catch (IOException e) {
            System.out.println(" meal to storage!");
        }
    }

    public ArrayList<Meal> getMeal(){return this.meals;}
}
