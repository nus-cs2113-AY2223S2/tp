package seedu.ui;

import seedu.entities.Exercise;
import seedu.entities.Meal;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class GeneralUi {
    public static Scanner sc = new Scanner(System.in);
    private static String endingMessage = "Bye! Hope to see you again soon!";
    private static String welcomeMessage = "Hello! I am LifeTracker, a program to aid you in keeping fit!" 
            + System.lineSeparator();

    /**
     * Reads user input
     * @return user input
     */
    public String readLine() {
        String command = "";
        while (command.length() == 0) {
            command = sc.nextLine();
            if (command.length() == 0) {
                System.out.println("Please enter a non-blank input!");
            } else {
                break;
            }
        }
        return command;
    }

    public int readInt() {
        String value = "";
        while (true) {
            value = sc.nextLine();
            if (value.length() == 0 || !value.matches("^([+-]?[1-9]\\d*|0)$")) {
                System.out.println("Please enter a valid integer!");
            } else {
                break;
            }
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
        System.out.println(meal);
    }

    public void printMealDeleted(Meal meal) {
        System.out.println("Successfully deleted this meal:");
        System.out.println(meal);
    }

    public void requestCalorieLimit() {
    }

    public void showCurrentIntake() {
    }

    public void showRemainingIntake() {

    }
    public void showDailyCaloricLimit() {
    }
    public void showWellDoneMessage(){
    }
    public static void displayDayCalories(ExerciseStorage exerciseStorage, LocalDate date, MealStorage mealStorage) {
        List<Exercise> exercisesOnSpecificDate = exerciseStorage.getExercisesByDate(date);
        float caloricDeficit = 0;
        for (Exercise exercise: exercisesOnSpecificDate){
            caloricDeficit += exercise.getCaloriesBurnt();
        }
        List<Meal> mealsOnSpecificDate = mealStorage.getMealByDate(date);
        float caloricGain = 0;
        for (Meal meal: mealsOnSpecificDate){
            caloricGain += meal.getTotalCalories();
        }
        float netCalories = caloricGain - caloricDeficit;
        System.out.println("Calories Gained on " + date + " : " + caloricGain);
        System.out.println("Calories Lost on  " + date + " : " + caloricDeficit);
        if (netCalories > 0) {
            System.out.println("You have gained " + netCalories + " calories on" + date);
        } else if (netCalories == 0){
            System.out.println("Your net calories on " + date + "is zero.");
        } else {
            System.out.println("You have lost " + netCalories + " calories on" + date);
        }
    }
}
