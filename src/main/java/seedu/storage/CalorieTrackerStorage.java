package seedu.storage;

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

/**
 * Represents a storage for CalorieTracker.
 */
public class CalorieTrackerStorage {
    private static final File defaultCalorieTrackerFile = new File("data/calorietracker.txt");
    private final File calorieTrackerFile;
    public CalorieTrackerStorage() {
        calorieTrackerFile = defaultCalorieTrackerFile;
    }

    public HashMap<Date, Integer> getUserData() {
        HashMap<Date, Integer> savedCalorieTracker = new HashMap<>();
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

    private void readCalorieTrackerFile(String line, HashMap<Date, Integer> totalCaloriesConsumedInDay)
            throws ParseException {
        String[] data = line.split(":");
        totalCaloriesConsumedInDay.put(DateFormatter.stringToDate(data[0]), Integer.valueOf(data[1]));
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

    public void saveUserData(HashMap<Date, Integer> totalCaloriesConsumedInDay) throws IOException {
        FileWriter fileWriter = new FileWriter(calorieTrackerFile);
        for (Date date : totalCaloriesConsumedInDay.keySet()) {
            fileWriter.write(DateFormatter.dateToString(date) + ':'
                    + totalCaloriesConsumedInDay.get(date) + System.lineSeparator());
        }
        fileWriter.close();
    }
}
