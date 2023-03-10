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
            // delete a recipe in list
        } else if (command[0].equals("delete")) {
            Recipe deletedRecipe = parser.parseDeleteRecipe(command, recipeList);
            ui.printMessage("Noted. I've removed this recipe:");
            ui.printMessage(deletedRecipe.toString());
            ui.printMessage("Now you have " + recipeList.size() + " recipes in the list.");
        } else if (command[0].equals("view")) {
            Recipe recipe = parser.parseViewRecipe(command, recipeList);
            ui.printRecipe(recipe);
        } else if (command[0].equals("list")) {
            parser.parseListRecipe(recipeList);
        } else if (command[0].equals("add")) {
            Recipe newRecipe = parser.parseAddRecipe(command, recipeList);
            ui.printMessage("I've added this new recipe:" + newRecipe.getName());
            ui.printMessage("Now you have " + recipeList.size() + " recipes in the list.");
        } else if (command[0].equals("edit")) {
            Recipe newrecipe = parser.parseEditRecipe(command, recipeList);
            // ui print message
            ui.printMessage("I've edited this recipe:" + newrecipe.getName());
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
