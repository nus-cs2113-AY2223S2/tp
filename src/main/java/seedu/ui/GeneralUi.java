package seedu.ui;

//import seedu.database.ExampleData;
import seedu.entities.Exercise;
import seedu.entities.Meal;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

import static java.lang.Math.abs;

public class GeneralUi {
    private static Scanner sc = new Scanner(System.in);
    private static int minimumNumber = 1; //inclusive
    private static int maximumNumber = 11; //exclusive
    private static Random random = new Random();
    private static int numberGenerated = random.nextInt(maximumNumber - minimumNumber) + minimumNumber;
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

    public float readFloat() {
        String value = "";
        while (true) {
            value = sc.nextLine();
            if (value.length() == 0 || 
                    !value.matches("^([0-9]+([.][0-9]*)?|[.][0-9]+)$")) {
                System.out.println("Invalid input, input is not a positive float value");
            } else {
                break;
            }
        }
        return Float.parseFloat(value);
    }

    /**
     * Helper function to print divider
     */
    public void printLine() {
        System.out.println("------------------------------------------------------------");
    }

    public void printMotivateMessage() {
        if (numberGenerated == 1) {
            System.out.println("You can do it!");
        } else if (numberGenerated == 2) {
            System.out.println("Keep it up!");
        } else if (numberGenerated == 3) {
            System.out.println("Good Progress!");
        } else if (numberGenerated == 4) {
            System.out.println("Well done!");
        } else if (numberGenerated == 5) {
            System.out.println("Good Work!");
        } else if (numberGenerated == 6) {
            System.out.println("Nice Effort!");
        } else if (numberGenerated == 7) {
            System.out.println("You are almost there!");
        } else if (numberGenerated == 8) {
            System.out.println("You can do it!");
        } else if (numberGenerated == 9) {
            System.out.println("Every step counts!");
        } else {
            System.out.println("Your goal is within reach!");
        }
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
        System.out.println("Please enter command 'help' if you require assistance.");
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

    public void printAllExercises(ExerciseStorage exerciseStorage) {
        if (exerciseStorage.getExercisesCount() == 0) {
            System.out.println("There are no exercises in your exercise list!");
        } else {
            System.out.println("Here are the exercises in your exercise list:");
            for (int i = 0; i < exerciseStorage.getExercisesCount(); i++) {
                String taskDescription = exerciseStorage.getExerciseById(i).toString();
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

    public void printExerciseDeleted(Exercise exercise) {
        System.out.println("Successfully deleted this exercise:");
        System.out.println(exercise);
    }

    public void requestCalorieLimit() {
    }

    public void showCurrentIntake() {
    }

    public void showRemainingIntake(double caloriesLeft) {

    }
    public void showDailyCaloricLimit(double caloricLimit) {
    }
    public void showWellDoneMessage(){
    }
    public void displayDayCalories(ExerciseStorage exerciseStorage, LocalDate date, MealStorage mealStorage) {
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
            System.out.println("You have gained " + netCalories + " calories on " + date);
        } else if (netCalories == 0) {
            System.out.println("Your net calories on " + date + "is zero.");
        } else {
            System.out.println("You have lost " + abs(netCalories) + " calories on " + date);
        }
    }

    public void printFieldNotStored() {
        System.out.println("The field has not been stored yet please update it.");
    }

    public void printName(String name){
        System.out.println("Name: " + name);
    }

    public void printWeight(float weight){
        System.out.println("Weight: " + weight + " kg");
    }

    public void printHeight(float height){
        System.out.println("Height: " + height + " cm");
    }
    public void printAge(int age){
        System.out.println("Age: " + age + " years old");
    }
    public void printGender(String gender){
        System.out.println("Gender: " + gender);
    }

    public void printHelpMenu(){
        System.out.println( "Here are the list of available commands: \n" + "[view]: Here's the list of information"
            + " you can choose to view followed by it's respective /[fieldName].\n"
            + "    1. Name: /name \n" + "    2. Weight: /weight\n" + "    3. Height: /height\n" + "    4. Age: /age\n"
            + "    5. Gender: /gender\n" + "    6. Daily caloric limit: /caloricLimit\n"
            + "    7. Calories left today: /caloriesLeft\n" + "    8. Target weight: /targetWeight\n"
            + "Usage: view /[fieldName]\n"+"[update]: Here's the list of information "
            + "you can choose to update followed by it's respective /[fieldName].\n" + "    1. Name: /name\n"
            + "    2. Weight: /weight\n" + "    3. Height: /height\n" + "    4. Age: /age\n"
            + "    5. Gender: /gender\n"+"    6. Target Weight: /targetWeight\n"
            + "Usage: update /[fieldName] [newInfo]\n"
            + "[add]: Add a meal.\n" + "Usage: add /on [date] /type [MealType] /foods [foods]\n"
            + "[list]: List either all the foods in the database or all previous added meals.\n"
            + "Usage: list foods / list meals\n" + "[delete]: Deletes a previously added meal.\n"
            + "Usage: delete [index]\n" + "[filter]: Filters food by calorie content.\n"
            +"[nutrition]: Find the nutrients of a specific kind of food.\n"+"[exercise]: Input a completed exercise.\n"
            + "Usage: exercise /type [exercise name] /description [exercise description] "
            + "/calories [calories burnt] /on [date]\n" + "[track]: Returns your caloric intake from previous days.\n"
            + "[examples]: Displays examples for inputs\n" + "[bye]: Exits the program.");
        System.out.println("For any other questions please visit out User Guide.\n");
    }
}


