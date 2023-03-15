package seedu.lifetracker;

import seedu.commands.Command;
import seedu.database.FoodStorage;
import seedu.database.MealStorage;
import seedu.database.UserStorage;
import seedu.exceptions.LifeTrackerException;
import seedu.parser.CommandParser;
import seedu.ui.GeneralUi;
import java.nio.file.Paths;

public class LifeTracker {
    private static final String PATH_HOME = System.getProperty("user.dir");
    private static final String FOOD_FILE_PATH = Paths.get(PATH_HOME, "data", "foodData.csv").toString();
    private static final String MEAL_FILE_PATH = Paths.get(PATH_HOME, "data", "mealData.csv").toString();
    private static final String USER_FILE_PATH = Paths.get(PATH_HOME, "data", "userData.csv").toString();
    private FoodStorage foodStorage;
    private MealStorage mealStorage;
    private UserStorage userStorage;
    private GeneralUi ui;

    public LifeTracker(String foodFilePath, String mealFilePath, String userFilePath) {
        foodStorage = new FoodStorage(foodFilePath);
        mealStorage = new MealStorage(mealFilePath, foodStorage);
        userStorage = new UserStorage(userFilePath);
        ui = new GeneralUi();
    }

    public void run() {
        assert false : "dummy assertion set to fail";
        ui.printIntroduction();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readLine();
                ui.printLine();
                Command command = CommandParser.parse(userInput);
                command.execute(ui, foodStorage, mealStorage, userStorage);
                isExit = command.isExit();
            } catch (LifeTrackerException e) {
                System.out.println(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        new LifeTracker(FOOD_FILE_PATH, MEAL_FILE_PATH, USER_FILE_PATH).run();
    }
}
