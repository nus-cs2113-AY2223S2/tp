package seedu.storage;

import seedu.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Represents a storage for FoodList.
 */
public class FoodListStorage {
    private static final File defaultFoodListFile = new File("data/foodlist.txt");
    private final File foodListFile;
    public FoodListStorage() {
        foodListFile = defaultFoodListFile;
    }

    HashMap<String, Integer> getUserData() {
        HashMap<String, Integer> savedFoodList = new HashMap<>();
        if (foodListFile.exists()) {
            try {
                Scanner scanner = new Scanner(foodListFile);
                while (scanner.hasNext()) {
                    readFoodListFileLine(scanner.nextLine(), savedFoodList);
                }
            } catch (FileNotFoundException e) {
                Ui.showReadFileErrorMessage("food list");
            }
        } else {
            Ui.showNoSavedDataMessage("food list");
            createFoodListFile();
        }
        Ui.showSuccessfulLoadDataMessage("food list");
        Ui.showOneLine();
        return savedFoodList;
    }

    private void readFoodListFileLine(String line, HashMap<String, Integer> savedFoodList) {
        String[] data = line.split(":");
        savedFoodList.put(data[0], Integer.valueOf(data[1]));
    }

    private void createFoodListFile() {
        try {
            if (foodListFile.createNewFile()) {
                Ui.showCreatedNewFileMessage("food list");
            }
        } catch (IOException e) {
            Ui.showNewFileNotCreatedMessage("food list");
        }
    }

    public void saveUserData(HashMap<String, Integer> foodCalories) throws IOException {
        FileWriter fileWriter = new FileWriter(foodListFile);
        for (String foodName : foodCalories.keySet()) {
            fileWriter.write(foodName + ':' + foodCalories.get(foodName) + System.lineSeparator());
        }
        fileWriter.close();
    }
}
