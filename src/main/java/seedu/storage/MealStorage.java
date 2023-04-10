package seedu.storage;

import java.io.BufferedReader;
import java.io.File;
// import java.io.File;
// import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import seedu.entities.Meal;
import seedu.exceptions.InvalidMealException;
import seedu.logger.LogFileHandler;
import seedu.constants.DateConstants;
import seedu.definitions.MealTypes;
import seedu.entities.Food;
import com.opencsv.CSVWriter;

public class MealStorage extends Storage implements FileReadable, FileWritable {
    private static final String CSV_DELIMITER = ",";
    private static final String FOODS_DELIMITER = "-";
    private static final DateTimeFormatter DTF = DateConstants.DATABASE_DTF;
    private ArrayList<Meal> meals;
    private FoodStorage foodStorage;
    private BufferedReader br;

    public MealStorage(String filePath, FoodStorage foodStorage) {
        super(filePath);
        this.foodStorage = foodStorage;
        meals = new ArrayList<Meal>();
        try {
            this.load();
            System.out.println("Initialised Meal Storage");
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
        String[] header = { "Date", "Foods", "Meal Type" };
        writer.writeNext(header);
        Collections.sort(meals);
        for (Meal meal : meals) {
            writer.writeNext(meal.toWriteFormat(FOODS_DELIMITER, DateConstants.DATABASE_FORMAT));
        }
        writer.close();
    }

    @Override
    public void load() throws IOException {
        String line = "";
        String[] mealLine;
        LocalDate date;
        String[] foodIndexes;
        ArrayList<Food> foods;
        MealTypes mealType;

        File storageFile = new File(filePath);
        if (!storageFile.getParentFile().exists()) {
            storageFile.getParentFile().mkdirs();
            storageFile.createNewFile();
        }

        br = new BufferedReader(new FileReader(filePath));

        // Skip Line 1 (header)
        br.readLine();

        while ((line = br.readLine()) != null) {
            mealLine = line.split(CSV_DELIMITER);
            date = LocalDate.parse(mealLine[0], DTF);

            foodIndexes = mealLine[1].split(FOODS_DELIMITER);
            foods = new ArrayList<Food>();
            for (String foodIndex : foodIndexes) {
                foods.add(foodStorage.getFoodById(Integer.parseInt(foodIndex)));
            }
            
            try {
                mealType = MealTypes.fromString(mealLine[2]);
                meals.add(new Meal(foods, date, mealType));
            } catch (InvalidMealException e) {
                LogFileHandler.logError(line + " has an invalid meal type");
            }
        }

        br.close();
    }

    public void saveMeal(Meal meal) {
        for (Meal m : meals) {
            if (!m.getDate().equals(meal.getDate()) || 
                    !m.getIdentifier().equals(meal.getIdentifier())) {
                continue;
            }
            m.addFoods(meal.getFoods());
            try {
                this.write();
                System.out.println("Meal was successfully added!");
            } catch (IOException e) {
                System.out.println("Could not add meal to storage!");
            }
            return;
        }
        
        meals.add(meal);
        try {
            this.write();
            System.out.println("Meal was successfully added!");
        } catch (IOException e) {
            System.out.println("Could not add meal to storage!");
        }
    }

    public void resetStorage() {
        meals = new ArrayList<Meal>();
        try {
            this.write();
            System.out.println("MealStorage was successfully resetted!");
        } catch (IOException e) {
            System.out.println("Could not reset MealStorage!");
        }
    }

    public int getMealCount() {
        return this.meals.size();
    }

    public ArrayList<Meal> getMeals() {
        return this.meals;
    }

    public List<Meal> getMealByDate(LocalDate date) {
        List<Meal> filteredMeals = meals.stream()
                .filter(m -> m.getDate().equals(date))
                .collect(Collectors.toList());
        return filteredMeals;
    }

    public Meal getMealById(int id) {
        return this.meals.get(id);
    }

    public Meal deleteMeal(int index) throws IndexOutOfBoundsException {
        return meals.remove(index);
    }
}
