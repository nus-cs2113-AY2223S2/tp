package seedu.storage.readfile;

import seedu.calorietracker.CalorieTracker;
import seedu.parser.DateFormatter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class DailyCaloriesReadFile {

    private static final String SPACE = " ";
    public static CalorieTracker readDailyCalorieToFile(String filePath) {

        CalorieTracker calorieTracker = new CalorieTracker();
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
                    String[] totalCalories = line.split(":");
                    String date  = totalCalories[0].trim();
                    Date formattedDate = DateFormatter.stringToDate(date);
                    int calories = Integer.parseInt(totalCalories[1].trim());
                    calorieTracker.updateTotalCalories(formattedDate, calories);

                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return calorieTracker;

    }
}
