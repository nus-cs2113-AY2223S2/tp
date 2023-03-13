package seedu.meal360;

public class Ui {

    private static final int BOXWIDTH = 100;
    private static final String SEPARATORCHAR = "-";

    public void printSeparator() {
        String separatorLine = SEPARATORCHAR.repeat(BOXWIDTH);
        System.out.println(separatorLine);
    }

    public void printWelcomeMessage() {
        printSeparator();
        String logo = " __  __          _ ____  __  __\n" + "|  \\/  |___ __ _| |__ / / / /  \\\n"
                + "| |\\/| / -_) _` | ||_ \\/ _ \\ () |\n" + "|_|  |_\\___\\__,_|_|___/\\___/\\__/\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");
        printSeparator();
    }

    public void printGoodbyeMessage() {
        printSeparator();
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
    }

    public String formatMessage(String message) {
        return String.format("| %-97s|", message);
    }

    public void printMessage(String message) {
        String outputMessage = formatMessage(message);
        System.out.println(outputMessage);
    }

    public void printRecipe(Recipe recipe) {
        printSeparator();
        System.out.println(formatMessage("Name of recipe: " + recipe.getName()));
        for (String ingredient : recipe.getIngredients().keySet()) {
            String outputMessage = String.format("%s(%d)", ingredient,
                    recipe.getIngredients().get(ingredient));
            System.out.println(formatMessage(outputMessage));
        }
        printSeparator();
    }

    public void listRecipe(RecipeList recipeListToPrint) {
        printSeparator();
        int numberOfRecipes = recipeListToPrint.size();
        int order = 0;
        if (numberOfRecipes > 0) {
            printMessage("These are the recipes you have (" + numberOfRecipes + " recipes):");
            for (Recipe recipe : recipeListToPrint) {
                order = order + 1;
                printMessage(order + ". " + recipe.getName() + "   (" + recipe.getNumOfIngredients() + " ingredients)");
            }
        } else {
            printMessage("There is nothing in the list. Please add a recipe.");
        }
        printSeparator();
    }
}
