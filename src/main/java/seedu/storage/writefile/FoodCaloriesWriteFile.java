package seedu.storage.writefile;

import seedu.calorietracker.FoodList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class FoodCaloriesWriteFile {
    private static final String SPACE = " ";

    public static void writeFoodCaloriesToFile(String filePath, FoodList foodList) {
        try {
            File savedFile = new File(filePath);
            FileWriter writeFile = new FileWriter(savedFile);
            HashMap<String, Integer> foodCalories = foodList.getFoodCalories();
            writeFile.write("Food Calories Look Up Table: " + System.lineSeparator());
            for (String foodName : foodCalories.keySet()) {
                writeFile.write(SPACE + foodName + ": " + foodCalories.get(foodName)
                        + " kcal" + System.lineSeparator());
            }
            writeFile.close();
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }
}
