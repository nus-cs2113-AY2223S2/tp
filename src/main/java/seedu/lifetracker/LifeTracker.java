package seedu.lifetracker;

import seedu.commands.Command;
import seedu.parser.CommandParser;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;

public class LifeTracker {
    private static final String MEAL_FILE_PATH = "./data/mealData.csv";
    private static final String USER_FILE_PATH = "./data/userData.csv";
    private static final String EXERCISE_FILE_PATH = "./data/exerciseData.csv";

    private FoodStorage foodStorage;
    private MealStorage mealStorage;
    private UserStorage userStorage;
    private ExerciseStorage exerciseStorage;
    private GeneralUi ui;

    public LifeTracker(String mealFilePath, String userFilePath, String exerciseFilePath) {
        foodStorage = new FoodStorage();
        mealStorage = new MealStorage(mealFilePath, foodStorage);
        userStorage = new UserStorage(userFilePath);
        exerciseStorage = new ExerciseStorage(exerciseFilePath);
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
                command.execute(ui, foodStorage, mealStorage, userStorage, exerciseStorage);
                isExit = command.isExit();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        new LifeTracker(MEAL_FILE_PATH, USER_FILE_PATH, EXERCISE_FILE_PATH).run();
    }
}
