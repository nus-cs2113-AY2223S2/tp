package seedu.meal360;

import java.time.format.DateTimeFormatter;

public class Ui {

    private static final int BOXWIDTH = 100;
    private static final String SEPARATORCHAR = "-";

    public void printSeparator() {
        String separatorLine = SEPARATORCHAR.repeat(BOXWIDTH);
        System.out.println(separatorLine);
    }

    public void printWelcomeMessage() {
        String logo = " __  __          _ ____  __  __\n" + "|  \\/  |___ __ _| |__ / / / /  \\\n"
                + "| |\\/| / -_) _` | ||_ \\/ _ \\ () |\n" + "|_|  |_\\___\\__,_|_|___/\\___/\\__/\n";
        System.out.println("Welcome to Meal360, your ultimate Recipe Manager!\n" + logo);
    }

    public void printGoodbyeMessage() {
        System.out.println(formatMessage("Bye. Hope to see you again soon!"));
    }

    public String formatMessage(String message) {
        return String.format("| %-97s|", message);
    }

    public void printMessage(String message) {
        String outputMessage = formatMessage(message);
        System.out.println(outputMessage);
    }

    /**
     * This method is designed to print the contents of a recipe.
     *
     * @param recipe recipe whose contents are to be printed.
     *
     **/
    public void printRecipe(Recipe recipe) {
        System.out.println(formatMessage("Name of recipe: " + recipe.getName()));
        int index = 1;
        for (String ingredient : recipe.getIngredients().keySet()) {
            String outputMessage = String.format("%s(%d)", ingredient,
                    recipe.getIngredients().get(ingredient));
            System.out.println(formatMessage(index + ". " + outputMessage));
            index++;
        }
    }

    /**
     * This method is designed to print the weeklyplan.
     *
     * @param weeklyPlan weeklyPlan whose contents are to be printed.
     *
     **/
    public void printWeeklyPlan(WeeklyPlan weeklyPlan) {
        if (weeklyPlan.isEmpty()) {
            printMessage("Your weekly plan is empty!");
        } else {
            printMessage("Here is your weekly plan:");
            weeklyPlan.forEach((recipe, count) -> {
                String outputMessage = String.format("%s x%d", recipe, count);
                printMessage(outputMessage);
            });
        }

    }

    /**
     * This method is designed to print the weekly ingredients.
     *
     * @param weeklyPlan weeklyPlan whose contents are to be printed.
     * @param recipeList recipeList of user
     *
     **/
    public void printWeeklyIngredients(WeeklyPlan weeklyPlan, RecipeList recipeList) {
        if (weeklyPlan.isEmpty()) {
            printMessage("Your weekly plan is empty!");
        } else {
            printMessage("Here are your weekly ingredients:");
            weeklyPlan.forEach((recipe, count) -> {
                Recipe currRecipe = recipeList.findByName(recipe.toString().trim());
                for (String ingredient : currRecipe.getIngredients().keySet()) {
                    String outputMessage = String.format("%s (%d)", ingredient,
                            currRecipe.getIngredients().get(ingredient) * count);
                    System.out.println(formatMessage(outputMessage));
                }
            });
        }

    }


    /**
     * This method is designed to print all the recipes in the user's recipe list.
     *
     * @param recipeListToPrint recipeList of user
     *
     **/
    public void listRecipe(RecipeList recipeListToPrint) {
        listRecipes(recipeListToPrint, "There is nothing to list.",
                "These are the recipes you have");
    }


    /**
     * This method is designed to print all the available recipes in the user's recipe list.
     *
     * @param recipeListToPrint recipeList of user
     *
     **/
    public void listAvailableRecipes(RecipeList recipeListToPrint) {
        listRecipes(recipeListToPrint, "There are no available recipes.",
                "These are the recipes you can cook");
    }

    /**
     * Print ordered list of recipes including recipes' name and ingredients
     *
     * @author notbingsu
     * @author junenita
     * @param recipeListToPrint list containing recipes to be printed
     * @param emptyListMsg      error message for empty list
     * @param listHeaderMsg     print list header message
     */
    private void listRecipes(RecipeList recipeListToPrint, String emptyListMsg, String listHeaderMsg) {
        int numberOfRecipes = recipeListToPrint.size();
        int order = 0;
        if (numberOfRecipes == 0) {
            printMessage(emptyListMsg);
            return;
        }
        printMessage(listHeaderMsg + " (" + numberOfRecipes + " recipes):");
        for (Recipe recipe : recipeListToPrint) {
            order++;
            printMessage(order + ". " + recipe.getName() + "   (" + recipe.getNumOfIngredients()
                    + " ingredients)");
        }
    }

    /**
     * This method is designed to display all the commands used by the
     * recipe manager to assist the user in executing the required
     * operations.
     *
     * @author AbijithRam
     */
    public void printHelp() {
        printMessage("These are the 21 operations you can do. Please follow the proper input"
                   + " formats while typing.");
        printSeparator();
        printMessage("1. Add Recipe: add /r {RECIPE_NAME}");
        printMessage("2. Edit Recipe: edit /r {RECIPE_NAME}");
        printMessage("3. View Recipe: view {RECIPE_INDEX}");
        printMessage("4. Delete Recipe: delete {RECIPE_INDEX} or delete {START_INDEX-END_INDEX} or");
        printMessage("                  delete /r {RECIPE_NAME} or delete /r all");
        printMessage("5. List All Recipes: list");
        printMessage("6. List Available Recipes: available");
        printMessage("7. View Weekly Plan: weeklyplan");
        printMessage("8. View Weekly Ingredients: weeklyingredients");
        printMessage("9. Add Single Recipe to Weekly Plan: weekly /add {RECIPE_NAME} {QUANTITY}");
        printMessage("10. Add Multiple Recipes to Weekly Plan: weekly /multiadd /r {RECIPE1_NAME} /q {QUANTITY1}");
        printMessage("                                        /r {RECIPE2_NAME} /q {QUANTITY2}");
        printMessage("11. Delete Single Recipe from Weekly Plan: weekly /delete {RECIPE_NAME} {QUANTITY}");
        printMessage("12. Delete Multiple Recipes from Weekly Plan: weekly /multidelete /r {RECIPE1_NAME}");
        printMessage("                                             /q {QUANTITY1} /r {RECIPE2_NAME} /q {QUANTITY2}");
        printMessage("13. Marking recipe in weekly plan as done: weekly /done {RECIPE_NAME}");
        printMessage("14. Clear weekly plan: weekly /clear");
        printMessage("15. Tagging/Categorizing Recipes: tag {LABEL_name} << {RECIPE_NAME && RECIPE_NAME && ...}");
        printMessage("16. Removing recipes from a Tag: tag {LABEL_name} >> {RECIPE_NAME && RECIPE_NAME && ...}");
        printMessage("17. Add User Ingredient: add_i /n {INGREDIENT_NAME} /c {QUANTITY} /d {DATE}");
        printMessage("18. Delete User Ingredient: del_i /n {INGREDIENT_NAME} /c {QUANTITY}");
        printMessage("19. View User Ingredients: view_ingredients");
        printMessage("20. Exit Recipe Manager: bye");
        printMessage("21. Help Tab: help");
        printSeparator();
        printMessage("NOTE:");
        printMessage("* Curly brackets {} are just to indicate what goes inside the command!");
        printMessage("* No need to type them while entering commands!");
    }


    /**
     * This method is designed to print the user ingredients.
     *
     * @param userIngredients ingredient list of user
     *
     **/
    public void printUserIngredients(IngredientList userIngredients) {
        if (userIngredients.isEmpty()) {
            printMessage("Your ingredient list is empty!");
        } else {
            printMessage("Here is your ingredient list:");
            userIngredients.forEach((name, ingredient) -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                String formattedDate = ingredient.expiryDate.format(formatter);
                String outputMessage = String.format("%s (%d) [by:%s]", name, ingredient.ingredientCount,
                        formattedDate);
                System.out.println(formatMessage(outputMessage));
            });
        }
    }

    /**
     * Extract message received whether users added or removed recipes from a tag.
     * Then, proceed to print a successful message.
     *
     * @author junenita
     * @param receivedMessage string containing 'add' or 'remove' tag and tag label
     */
    public void printTagMessage(String receivedMessage) {
        String[] args = receivedMessage.split(" ", 2);
        String command = args[0].trim();
        String tag = args[1].trim();
        if (command.equals("add")) {
            printSuccessfullyAddTag(tag);
        } else if (command.equals("remove")) {
            printSuccessfullyRemoveTag(tag);
        }
    }

    /**
     * Print successfully add recipes to the tag
     *
     * @author junenita
     * @param tag tag label
     */
    public void printSuccessfullyAddTag(String tag) {
        printMessage("You have successfully added the recipe(s) to \"" + tag + "\" tag.");
    }

    /**
     * Print successfully remove recipes from the tag
     *
     * @author junenita
     * @param tag tag label
     */
    public void printSuccessfullyRemoveTag(String tag) {
        printMessage("You have successfully removed the recipe(s) from \"" + tag + "\" tag.");
    }

    /**
     * Print random result header
     *
     * @author junenita
     */
    public void printRandomMessage() {
        printMessage("Random Result.....");
    }
}
