package seedu.meal360;

import java.util.HashMap;
import java.util.Scanner;

public class Meal360 {

    /**
     * Main entry-point for the java.duke.Meal360 application.
     */

    private static Boolean canExit = false;
    private static final Ui ui = new Ui();
    private static final Parser parser = new Parser();
    private static final Database database = new Database();
    private static final RecipeList recipeList = new RecipeList();

    public static void startApp() {
        ui.printWelcomeMessage();
        // Dummy recipe for testing purposes
        HashMap<String, Integer> testIngredients = new HashMap<>();
        testIngredients.put("test ingredient", 100);
        Recipe testR = new Recipe("test recipe name", testIngredients);
        recipeList.addRecipe(testR);

    }

    public static void receiveInput(String input) {
        String[] command = input.trim().split(" ");
        if (input.equalsIgnoreCase("bye")) {
            canExit = true;
        } else if (command[0].equals("view")) {
            Recipe recipe = parser.parseViewRecipe(command, recipeList);
            ui.printRecipe(recipe);
        } else if (command[0].equals("list")) {
            RecipeList recipeListToPrint = parser.parseListRecipe(recipeList);
            ui.listRecipe(recipeListToPrint);
        }
    }

    public static void exitApp() {
        ui.printGoodbyeMessage();
    }

    public static void main(String[] args) {
        startApp();

        String line;
        Scanner userInput = new Scanner(System.in);

        do {
            line = userInput.nextLine();
            receiveInput(line);
        } while (!canExit);

        exitApp();
    }
}
