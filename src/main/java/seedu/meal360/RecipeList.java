package seedu.meal360;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipeList extends ArrayList<Recipe> {
    private static final Ui ui = new Ui();
    public void addRecipe(Recipe recipe) {

    }

    public void editRecipe(String name, HashMap<String, Integer> ingredients) {

    }

    public void deleteRecipe(String name) {

    }

    // list recipe's name and ingredients
    public void listRecipes() {
        ui.printSeparator();
        int numberOfRecipes = this.size();
        int order = 0;
        if (numberOfRecipes > 0) {
            ui.printMessage("These are the recipes you have (" + numberOfRecipes + " recipes) :");
            for (Recipe recipe : this) {
                order = order + 1;
                ui.printMessage(order + ". " + recipe.getName() + "   ("
                        + recipe.getNumOfIngredients() + " ingredients)");
            }
        } else {
            ui.printMessage("There is nothing in the list. Please add a recipe.");
        }
        ui.printSeparator();
    }
}
