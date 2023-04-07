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

    public void listRecipe(RecipeList recipeListToPrint) {
        listRecipes(recipeListToPrint, "There is nothing to list.",
                "These are the recipes you have");
    }

    public void listAvailableRecipes(RecipeList recipeListToPrint) {
        listRecipes(recipeListToPrint, "There are no available recipes.",
                "These are the recipes you can cook");
    }

    private void listRecipes(RecipeList recipeListToPrint, String emptyListMsg,
                             String listHeaderMsg) {
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

    public void printHelp() {
        printMessage("These are the operations you can do. Please follow the proper input"
                + " formats while typing.");
        printMessage("1. Add Recipe: add /r {recipe name}");
        printMessage("2. View Recipe: view {index number}");
        printMessage("3. Edit Recipe: edit {recipe name}");
        printMessage("4. Delete Recipe: delete {index number} or delete {starting index-ending index} or");
        printMessage("delete /r {recipe name} or delete /r all");
        printMessage("5. List All Recipes: list");
        printMessage("6. Add Single Recipe to Weekly Plan: weekly /add {recipe name} {quantity}");
        printMessage("7. Add Multiple Recipes to Weekly Plan: weekly /multiadd /r {recipe1 name} /q {quantity1}");
        printMessage("   /r {recipe2 name} /q {quantity2}");
        printMessage("8. Delete Single Recipe from Weekly Plan: weekly /delete {recipe name} {quantity}");
        printMessage("9. Delete Multiple Recipes from Weekly Plan: weekly /multidelete /r {recipe1 name}");
        printMessage("   /q {quantity1} /r {recipe2 name} /q {quantity2}");
        printMessage("10. View Weekly Plan: weeklyplan");
        printMessage("11. Clearing weekly plan: weekly /clear");
        printMessage("12. View Weekly Ingredients: weeklyingredients");
        printMessage("13. Give a random recipe: random");
        printMessage("14. Tagging/Categorizing Recipes: tag {LABEL_name} << {RECIPE_NAME && RECIPE_NAME && ...}");
        printMessage("15. Removing recipes from a Tag: tag {LABEL_name} >> {RECIPE_NAME && RECIPE_NAME && ...}");
        printMessage("16. Exit: bye");
        printMessage("HOW TO ADD INGREDIENTS?");
        printMessage("ingredient1_name=ingredient1_quantity ingredient2_name=ingredient2_quantity ...");
    }

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

    public void printSuccessfullyAddTag(String tag) {
        printMessage("You have successfully added the recipe(s) to \"" + tag + "\" tag.");
    }

    public void printSuccessfullyRemoveTag(String tag) {
        printMessage("You have successfully removed the recipe(s) from \"" + tag + "\" tag.");
    }
}
