package seedu.duke;

import seedu.Commands.Command;
import seedu.Database.FoodStorage;
import seedu.Database.MealStorage;
import seedu.Database.UserStorage;
import seedu.Entities.Food;
import seedu.Exceptions.LifeTrackerException;
import seedu.Parser.CommandParser;

import java.io.IOException;
import java.util.Scanner;

public class LifeTracker {

    private static final String FOOD_FILE_PATH = "data/FoodData.csv";
    private static final String MEAL_FILE_PATH = "data/MealData.csv";
    private static final String USER_FILE_PATH = "data/userData.csv";
    private static Scanner scanner = new Scanner(System.in);
    private FoodStorage foodStorage;
    private MealStorage mealStorage;
    private UserStorage userStorage;

    public LifeTracker(String filePath) {
        try {
            foodStorage = new FoodStorage(FOOD_FILE_PATH);
            foodList.load();
        } catch (IOException e) {
            System.out.println("Error loading Food Storage");
        }

        try {
            mealStorage = new MealStorage(MEAL_FILE_PATH, foodStorage);
            mealStorage.load();
        } catch (IOException e) {
            System.out.println("Error loading Meal Storage");
        }

        try {
            userStorage = new UserStorage(FOOD_FILE_PATH);
            userStorage.load();
        } catch (IOException e) {
            System.out.println("Error loading User Storage");
        }
    }

    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = readUserInput();
                Command command = CommandParser.parse(userInput);
                command.execute(foodList, userList);
            } catch (LifeTrackerException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String readUserInput (){
        String userInput = "";
        while (userInput.length() == 0) {
            userInput = scanner.nextLine();
        }
        return userInput;
    }

    public static void main(String[] args) {
        LifeTracker lifeTracker = new LifeTracker(filePath).run();
    }
}
