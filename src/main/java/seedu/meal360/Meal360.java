package seedu.meal360;

import java.io.IOException;
import java.util.Scanner;

import seedu.meal360.exceptions.IngredientNotFoundException;
import seedu.meal360.exceptions.InvalidRecipeNameException;
import seedu.meal360.exceptions.InvalidValueException;
import seedu.meal360.exceptions.NoRecipeException;
import seedu.meal360.exceptions.RecipeNotFoundInTagException;
import seedu.meal360.exceptions.TagNotFoundException;
import seedu.meal360.storage.Database;

public class Meal360 {

    /**
     * Main entry-point for the java.duke.Meal360 application.
     */

    private static Boolean canExit = false;
    private static final Ui ui = new Ui();
    private static final Parser parser = new Parser();
    private static final Database database = new Database();
    private static RecipeList recipeList = new RecipeList();
    private static WeeklyPlan weeklyPlan = new WeeklyPlan();

    private static IngredientList userIngredients = new IngredientList();



    /**
     * This method is designed to execute when the application is started.
     * It loads in the recipes, weekly plan and ingredients if they exist.
     *
     **/
    public static void startApp() {
        ui.printSeparator();
        ui.printWelcomeMessage();
        ui.printSeparator();

        // Load databases
        try {
            ui.printMessage("Loading recipes...");
            recipeList = database.loadRecipesDatabase();
            ui.printMessage("Recipes loaded successfully.");
        } catch (Exception e) {
            ui.printMessage("Error loading recipes, loading default recipes instead.");
            recipeList = database.defaultRecipeList();
        }

        try {
            ui.printMessage("Loading weekly plan...");
            weeklyPlan = database.loadWeeklyPlanDatabase();
            boolean isClean = weeklyPlan.checkValidity(recipeList);
            if (!isClean) {
                ui.printMessage("Weekly plan has invalid recipes, removing them...");
            }
            ui.printMessage("Weekly plan loaded successfully.");
        } catch (Exception e) {
            ui.printMessage("Error loading weekly plan, loading default empty weekly plan instead.");
            weeklyPlan = new WeeklyPlan();
        }

        try {
            ui.printMessage("Loading ingredients...");
            userIngredients = database.loadUserIngredientsDatabase();
            ui.printMessage("Ingredients loaded successfully.");
        } catch (Exception e) {
            ui.printMessage("Error loading ingredients, loading default empty list of ingredients instead.");
            userIngredients = new IngredientList();
        }

        ui.printSeparator();
    }

    /**
     * This method is designed to receive the user input and perform the
     * required tasks.
     *
     **/
    public static void receiveInput(String input) {
        String[] command = parser.cleanUserInput(input);

        if (input.trim().equalsIgnoreCase("bye")) {
            canExit = true;
        } else if (command[0].equals("delete")) {
            ui.printSeparator();
            try {
                String deletedRecipe = parser.parseDeleteRecipe(command, recipeList);
                ui.printMessage("Noted. I've removed this recipe:");
                ui.printMessage(deletedRecipe);
                ui.printMessage("Now you have " + recipeList.size() + " recipes in the list.");
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printMessage(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.printMessage(e.getMessage());
            }
            ui.printSeparator();
        } else if (command[0].equals("view")) {
            ui.printSeparator();
            try {
                Recipe recipe = parser.parseViewRecipe(command, recipeList);
                ui.printRecipe(recipe);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                ui.printMessage(e.getMessage());
            }
            ui.printSeparator();
        } else if (command[0].equals("list")) {
            try {
                ui.printSeparator();
                RecipeList recipeListToPrint = parser.parseListRecipe(command, recipeList);
                ui.listRecipe(recipeListToPrint);
            } catch (TagNotFoundException | NoRecipeException | IllegalArgumentException | IndexOutOfBoundsException
                     | NullPointerException e) {
                ui.printMessage(e.getMessage());
            }
            ui.printSeparator();
        } else if (command[0].equals("add")) {
            ui.printSeparator();
            try {
                Recipe newRecipe = parser.parseAddRecipe(command, recipeList);
                ui.printMessage("I've added this new recipe:" + newRecipe.getName());
                ui.printMessage("Now you have " + recipeList.size() + " recipes in the list.");
            } catch (ArrayIndexOutOfBoundsException e) {
                String errorMessage = "Please enter a valid recipe name.";
                ui.printMessage(errorMessage);
            } catch (NullPointerException e) {
                String errorMessage = "Recipe already exists. Add a new recipe.";
                ui.printMessage(errorMessage);
            }
            ui.printSeparator();
        } else if (command[0].equals("edit")) {
            ui.printSeparator();
            try {
                Recipe recipeToEdit = parser.parseEditRecipe(command, recipeList);
                ui.printSeparator();
                ui.printMessage("I've edited this recipe:" + recipeToEdit.getName());
            } catch (NumberFormatException e) {
                String errorMessage = String.format(
                        "Please enter a valid recipe number. You entered %s, " + "which is not a number.",
                        command[1]);
                ui.printMessage(errorMessage);
            } catch (ArrayIndexOutOfBoundsException e) {
                String errorMessage = "Please enter a valid recipe name.";
                ui.printMessage(errorMessage);
            } catch (IndexOutOfBoundsException e) {
                String errorMessage = String.format(
                        "Please enter a valid recipe number. You entered %s, " + "which is out of bounds.",
                        command[1]);
                ui.printMessage(errorMessage);
            } catch (NullPointerException e) {
                String errorMessage = "Recipe doesn't exist for editing.";
                ui.printMessage(errorMessage);
            }
            ui.printSeparator();
        } else if (command[0].equals("tag")) {
            try {
                ui.printSeparator();
                String returnMessage = parser.parseTagRecipe(command, recipeList);
                ui.printTagMessage(returnMessage);
            } catch (IllegalArgumentException | IndexOutOfBoundsException | NullPointerException
                     | RecipeNotFoundInTagException | TagNotFoundException | NoRecipeException e) {
                ui.printMessage(e.getMessage());
            }
            ui.printSeparator();
        } else if (command[0].equals("random")) {
            ui.printSeparator();
            try {
                Recipe randomRecipe = parser.parseRandomRecipe(recipeList);
                ui.printRandomMessage();
                ui.printRecipe(randomRecipe);
            } catch (NoRecipeException e) {
                ui.printMessage(e.getMessage());
            }
            ui.printSeparator();
        } else if (command[0].equals("weekly")) {
            try {
                ui.printSeparator();
                WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipeList);

                switch (command[1]) {
                case "/add":
                case "/multiadd":
                    weeklyPlan.addPlans(recipeMap);
                    ui.printMessage("I've added the recipes to your weekly plan!");
                    break;
                case "/delete":
                case "/multidelete":
                    weeklyPlan.deletePlans(recipeMap);
                    ui.printMessage("I've deleted the recipes from your weekly plan!");
                    break;
                case "/clear":
                    weeklyPlan.clearPlan();
                    ui.printMessage("I've cleared your entire weekly plan!");
                    break;
                case "/done":
                    String recipeName = parser.parseMarkDone(command, userIngredients, weeklyPlan,
                            recipeList);
                    weeklyPlan.markDone(recipeName, recipeList, userIngredients);
                    ui.printMessage("I've recorded that you've done this recipe!");
                    ui.printMessage("Ingredients list has been updated accordingly!");
                    break;
                default:
                    ui.printMessage("Please enter a valid command.");
                    break;
                }
            } catch (IllegalArgumentException | InvalidValueException | InvalidRecipeNameException
                     | ArrayIndexOutOfBoundsException | IngredientNotFoundException e) {
                ui.printMessage(e.getMessage());
            }
            ui.printSeparator();
        } else if (command[0].equals("available")) {
            // list recipes with ingredients all in ingredient list
            ui.printSeparator();
            RecipeList availableRecipes = recipeList.availableRecipes();
            ui.listAvailableRecipes(availableRecipes);
            ui.printSeparator();
        } else if (command[0].equals("weeklyingredients")) {
            ui.printSeparator();
            ui.printWeeklyIngredients(weeklyPlan, recipeList);
            ui.printSeparator();
        } else if (command[0].equals("weeklyplan")) {
            ui.printSeparator();
            ui.printWeeklyPlan(weeklyPlan);
            ui.printSeparator();
        } else if (command[0].equals("add_i")) {
            ui.printSeparator();
            try {
                Ingredient ingredientToAdd = parser.parseAddUserIngredients(command);
                userIngredients.addIngredient(ingredientToAdd);
                ui.printMessage("Ingredient successfully added!");
            } catch (IllegalArgumentException | IndexOutOfBoundsException | InvalidValueException e) {
                ui.printMessage(e.getMessage());
            }
            ui.printSeparator();
        } else if (command[0].equals("del_i")) {
            ui.printSeparator();
            try {
                Ingredient ingredientToDelete = parser.parseDeleteUserIngredients(command);
                userIngredients.deleteIngredient(ingredientToDelete);
                ui.printMessage("Ingredients successfully deleted!");
            } catch (IllegalArgumentException | IndexOutOfBoundsException | IngredientNotFoundException
                     | InvalidValueException e) {
                ui.printMessage(e.getMessage());
            }
            ui.printSeparator();
        } else if (command[0].equals("view_ingredients")) {
            ui.printSeparator();
            ui.printUserIngredients(userIngredients);
            ui.printSeparator();
        } else if (command[0].equals("help")) {
            ui.printSeparator();
            ui.printHelp();
            ui.printSeparator();
        } else {
            ui.printSeparator();
            ui.printMessage("I'm sorry, but I don't know what that means :-(");
            ui.printSeparator();
        }
    }

    /**
     * This method is designed to exit the application.
     *
     * It saves the recipes, weekly plan and ingredients if any entered by the user.
     *
     **/
    public static void exitApp() {
        ui.printSeparator();

        // Save database
        ui.printMessage("Saving recipes...");
        try {
            database.saveRecipesDatabase(recipeList);
            ui.printMessage("Recipes saved successfully.");
        } catch (IOException error) {
            ui.printMessage("Error saving database.");
        }

        // Save weekly plan
        ui.printMessage("Saving weekly plan...");
        try {
            database.saveWeeklyPlanDatabase(weeklyPlan);
            ui.printMessage("Weekly plan saved successfully.");
        } catch (IOException error) {
            ui.printMessage("Error saving weekly plan.");
        }

        // Save ingredients
        ui.printMessage("Saving ingredients...");
        try {
            database.saveIngredientsDatabase(userIngredients);
            ui.printMessage("Ingredients saved successfully.");
        } catch (IOException error) {
            ui.printMessage("Error saving ingredients.");
        }

        ui.printGoodbyeMessage();
        ui.printSeparator();
    }

    public static void main(String[] args) {
        startApp();

        String line;
        Scanner userInput = new Scanner(System.in);

        try {
            do {
                line = userInput.nextLine();
                receiveInput(line);
            } while (!canExit);
        } catch (Exception error) {
            ui.printMessage("Force exit detected, attempting to save data...");
        } finally {
            exitApp();
        }
    }
}
