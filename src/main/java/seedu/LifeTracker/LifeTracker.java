package seedu.LifeTracker;

import seedu.Commands.Command;
import seedu.Database.FoodStorage;
import seedu.Database.MealStorage;
import seedu.Database.UserStorage;
import seedu.Exceptions.LifeTrackerException;
import seedu.Parser.CommandParser;
import seedu.Ui.GeneralUi;

public class LifeTracker {
    private static final String FOOD_FILE_PATH = "./data/FoodData.csv";
    private static final String MEAL_FILE_PATH = "./data/MealData.csv";
    private static final String USER_FILE_PATH = "./data/userData.csv";
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
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        new LifeTracker(FOOD_FILE_PATH, MEAL_FILE_PATH, USER_FILE_PATH).run();
    }
}
