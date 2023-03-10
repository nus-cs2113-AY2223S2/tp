package seedu.meal360;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipeList extends ArrayList<Recipe> {
    private static final Ui ui = new Ui();

    public Recipe findByName(String name) {
        for (Recipe recipe : this) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                return recipe;
            }
        }
        return null;
    }

    public void addRecipe(Recipe recipe) {
        super.add(recipe);
    }

    public void editRecipe(Recipe recipe, HashMap<String, Integer> ingredients) {
        recipe.ingredients = ingredients;
    }

    public Recipe deleteRecipe(int recipeNum) {
        Recipe recipeToDelete = super.get(recipeNum);
        super.remove(recipeToDelete);
        return recipeToDelete;
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
                ui.printMessage(order + ". " + recipe.getName() + "   (" + recipe.getNumOfIngredients() + " ingredients)");
            }
        } else {
            ui.printMessage("There is nothing in the list. Please add a recipe.");
        }
        ui.printSeparator();
    }
}
