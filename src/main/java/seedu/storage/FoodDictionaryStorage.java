package seedu.storage;

import seedu.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

//@@author calebcjl
/**
 * Represents a storage for FoodDictionary.
 */
public class FoodDictionaryStorage {
    private static final File defaultFoodDictionaryFile = new File("data/fooddict.txt");
    private final File foodDictionaryFile;
    public FoodDictionaryStorage() {
        foodDictionaryFile = defaultFoodDictionaryFile;
    }

    HashMap<String, Integer> getUserData() {
        HashMap<String, Integer> savedFoodDictionary = new HashMap<>();
        if (foodDictionaryFile.exists()) {
            try {
                Scanner scanner = new Scanner(foodDictionaryFile);
                while (scanner.hasNext()) {
                    readFoodDictionaryFileLine(scanner.nextLine(), savedFoodDictionary);
                }
            } catch (FileNotFoundException e) {
                Ui.showReadFileErrorMessage("food dictionary");
            }
        } else {
            Ui.showNoSavedDataMessage("food dictionary");
            createFoodDictionaryFile();
        }
        Ui.showSuccessfulLoadDataMessage("food dictionary");
        Ui.showOneLine();
        return savedFoodDictionary;
    }

    private void readFoodDictionaryFileLine(String line, HashMap<String, Integer> savedFoodDictionary) {
        String[] data = line.split(":");
        savedFoodDictionary.put(data[0], Integer.valueOf(data[1]));
    }

    private void createFoodDictionaryFile() {
        try {
            if (foodDictionaryFile.createNewFile()) {
                Ui.showCreatedNewFileMessage("food dictionary");
            }
        } catch (IOException e) {
            Ui.showNewFileNotCreatedMessage("food dictionary");
        }
    }

    void saveUserData(HashMap<String, Integer> foodCalories) throws IOException {
        FileWriter fileWriter = new FileWriter(foodDictionaryFile);
        for (String foodName : foodCalories.keySet()) {
            fileWriter.write(foodName + ':' + foodCalories.get(foodName) + System.lineSeparator());
        }
        fileWriter.close();
    }
}
