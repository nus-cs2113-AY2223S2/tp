package seedu.storage;

import seedu.calorietracker.Food;
import seedu.calorietracker.FoodList;
import seedu.parser.DateFormatter;
import seedu.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

//@@author calebcjl
/**
 * Represents a storage for CalorieTracker.
 */
public class CalorieTrackerStorage {
    private static final File defaultCalorieTrackerFile = new File("data/calorietracker.txt");
    private final File calorieTrackerFile;
    public CalorieTrackerStorage() {
        calorieTrackerFile = defaultCalorieTrackerFile;
    }

    public HashMap<Date, FoodList> getUserData() {
        HashMap<Date, FoodList> savedCalorieTracker = new HashMap<>();
        if (calorieTrackerFile.exists()) {
            try {
                Scanner scanner = new Scanner(calorieTrackerFile);
                while (scanner.hasNext()) {
                    readCalorieTrackerFile(scanner.nextLine(), savedCalorieTracker);
                }
            } catch (FileNotFoundException | ParseException e) {
                Ui.showReadFileErrorMessage("calorie tracker");
            }
        } else {
            Ui.showNoSavedDataMessage("calorie tracker");
            createCalorieTrackerFile();
        }
        Ui.showSuccessfulLoadDataMessage("calorie tracker");
        Ui.showOneLine();
        return savedCalorieTracker;
    }

    private void readCalorieTrackerFile(String line, HashMap<Date, FoodList> dailyFoodConsumption)
            throws ParseException {
        String[] data = line.split(",");
        Date date = DateFormatter.stringToDate(data[0]);
        FoodList foodList = new FoodList();
        for (int i = 1; i < data.length; i += 2) {
            Food food = new Food(data[i], Integer.parseInt(data[i + 1]));
            foodList.addFood(food);
        }
        dailyFoodConsumption.put(date, foodList);
    }

    private void createCalorieTrackerFile() {
        try {
            if (calorieTrackerFile.createNewFile()) {
                Ui.showCreatedNewFileMessage("calorie tracker");
            }
        } catch (IOException e) {
            Ui.showNewFileNotCreatedMessage("calorie tracker");
        }
    }

    void saveUserData(HashMap<Date, FoodList> dailyFoodConsumption) throws IOException {
        FileWriter fileWriter = new FileWriter(calorieTrackerFile);
        for (Date date : dailyFoodConsumption.keySet()) {
            fileWriter.write(DateFormatter.dateToString(date) + ',');
            for (Food food : dailyFoodConsumption.get(date).getFoods()) {
                fileWriter.write(food.getFoodName() + ',' + food.getCalories() + ',');
            }
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }
}
