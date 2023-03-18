package seedu.output;

import seedu.database.FoodStorage;
import seedu.entities.Food;

import java.util.Scanner;

public class UI {
    public static Scanner sc = new Scanner(System.in);

    /**
     * Reads user input
     *
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
     * Prints Welcome message when Duke first starts
     */
    public void printIntroduction() {
        this.printLine();
        System.out.println("Hello! Welcome to");
        this.printLogo();
        this.printLine();
        System.out.println();
    }

    /**
     * Prints Goodbye message when Duke exits
     */
    public void printGoodbye() {
        System.out.println();
        this.printLine();
        System.out.println("Bye. Hope to see you again soon!");
        this.printLine();
    }

    /**
     * Helper function to print divider
     */
    public void printLine() {
        System.out.println("------------------------------------------------------------");
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

    public void printNewFoodAdded(FoodStorage foodStorage, int food) {
        Food newFood = foodStorage.getFoodById(food);
        String foodDescription = newFood.toString();
        System.out.println("You just added this food"+ System.lineSeparator() + foodDescription);
    }

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

    public void printDeletedFood(Food newFood) {
    }
}
