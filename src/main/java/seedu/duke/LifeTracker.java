package seedu.duke;

import seedu.Commands.Command;
import seedu.Database.FoodStorage;
import seedu.Database.UserStorage;
import seedu.Entities.Food;
import seedu.Exceptions.LifeTrackerException;
import seedu.Parser.CommandParser;

import java.io.IOException;
import java.util.Scanner;

public class LifeTracker {

    protected static String filePath = "data/storage.csv";
    public static Scanner scanner = new Scanner(System.in);
    private FoodStorage foodList;
    private UserStorage userList;

    public LifeTracker(String filePath) {
        try {
            foodList = new FoodStorage(filePath);
            foodList.load();
        } catch (IOException e) {
            foodList = new FoodStorage(filePath);
            throw new RuntimeException(e);
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
        new LifeTracker(filePath).run();
    }
}
