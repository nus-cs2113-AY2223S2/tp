package seedu.storage.writefile;

import seedu.calorietracker.CalorieTracker;
import seedu.parser.DateFormatter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class DailyCaloriesWriteFile {

    private static final String SPACE = " ";

    public static void writeDailyCaloriesToFile(String filePath, CalorieTracker calorieTracker) {
        try {
            File savedFile = new File(filePath);
            FileWriter writeFile = new FileWriter(savedFile);
            HashMap<Date, Integer> totalCalories = calorieTracker.getTotalCaloriesConsumedInDay();
            writeFile.write("Daily Calories Intake: " + System.lineSeparator());
            for (Date date : totalCalories.keySet()) {
                String formattedDate = DateFormatter.dateToString(date);
                writeFile.write(SPACE + formattedDate + ": " + totalCalories.get(date)
                        + System.lineSeparator());
            }
            writeFile.close();
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }
}
