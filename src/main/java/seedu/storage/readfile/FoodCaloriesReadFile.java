package seedu.storage.readfile;

import seedu.calorietracker.FoodList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FoodCaloriesReadFile {

    private static final String SPACE = " ";

    public static FoodList readFoodCalorieToFile(String filePath){
        FoodList foodList = new FoodList();
        // Read from text file and update foodList
        File savedFile = new File(filePath);
        if (!savedFile.getParentFile().exists()) {
            savedFile.getParentFile().mkdirs();
        }
        try {
            if (!savedFile.exists()) {
                savedFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Failed to create a new file!!!");
        }

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith(SPACE)) {
                    String[] foodCalories = line.split(":");
                    String food = foodCalories[0].trim();
                    String[] caloriesWithUnit = foodCalories[1].trim().split(" ");
                    int calories = Integer.parseInt(caloriesWithUnit[0]);
                    foodList.addFood(food, calories);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return foodList;
    }
}
