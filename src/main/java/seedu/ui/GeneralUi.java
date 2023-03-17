package seedu.ui;

import seedu.database.FoodStorage;
import seedu.database.MealStorage;
import seedu.entities.Meal;

import java.util.Scanner;

public class GeneralUi {
    public static Scanner sc = new Scanner(System.in);
    private static String endingMessage = "Bye! Hope to see you again soon!";
    private static String welcomeMessage = "Hello! I am LifeTracker, a program to aid you in keeping fit!" + System.lineSeparator();

    /**
     * Reads user input
     * @return user input
     */
    public String readLine() {
        String command = "";
        while (command.length() == 0) {
            command = sc.nextLine();
        }
        return command;
    }

    public int readInt() {
        String value = "";
        while (value.length() == 0 || !value.matches("^([+-]?[1-9]\\d*|0)$")) {
            value = sc.nextLine();
        }
        return Integer.parseInt(value);
    }

    /**
     * Helper function to print divider
     */
    public void printLine() {
        System.out.println("------------------------------------------------------------");
    }


    /**
     * Prints Goodbye message when Duke exits
     */
    public void printGoodbye() {
        System.out.println();
        this.printLine();
        System.out.println(endingMessage);
        this.printLine();
    }

    private void printLogo() {
        System.out.println(
                " _      _  __  _______             _              " + System.lineSeparator()
                        + "| |    (_)/ _||__   __|           | |            " + System.lineSeparator()
                        + "| |     _| |_ ___| |_ __ __ _  ___| | _____ _ __ " + System.lineSeparator()
                        + "| |    | |  _/ _ \\ | '__/ _` |/ __| |/ / _ \\ '__|" + System.lineSeparator()
                        + "| |____| | ||  __/ | | | (_| | (__|   <  __/ |   " + System.lineSeparator()
                        + "|______|_|_| \\___|_|_|  \\__,_|\\___|_|\\_\\___|_|   " + System.lineSeparator()
        );
    }
    /**
     * Prints Welcome message when Duke first starts
     */
    public void printIntroduction() {
        this.printLine();
        System.out.println("Hello! Welcome to");
        this.printLogo();
        this.printLine();
        System.out.println(welcomeMessage);
    }
    public void requestWeight(){}
    public void showLatestWeight(int weight){}

    public void printAllFoods(FoodStorage foodStorage) {
        if (foodStorage.getFoodsCount() == 0) {
            System.out.println("There are no foods in your food list!");
        } else {
            System.out.println("Here are the foods in your food list:");
            for (int i = 0; i < foodStorage.getFoodsCount(); i++) {
                String taskDescription = foodStorage.getFoodById(i).toString();
                System.out.println((i + 1) + ". " + taskDescription);
            }
        }
    }

    public void printAllMeals(MealStorage mealStorage) {
        if (mealStorage.getMealCount() == 0) {
            System.out.println("There are no meals in your meal list!");
        } else {
            System.out.println("Here are the meals in your meal list:");
            for (int i = 0; i < mealStorage.getMealCount(); i++) {
                String taskDescription = mealStorage.getMealById(i).toString();
                System.out.println((i + 1) + ". " + taskDescription);
            }
        }
    }

    public void printNewMealAdded(Meal meal) {
        System.out.println("You just added this meal");
        System.out.println(meal);
    }

    public void printMealDeleted(Meal meal) {
        System.out.println("Successfully deleted this meal:");
        System.out.println(meal);
    }

    public void requestCalorieLimit(){
    }

    public void showCurrentIntake(){}

    public void showRemainingIntake(){}
    public void showDailyCaloricLimit(){}
    public void showWellDoneMessage(){}
}
