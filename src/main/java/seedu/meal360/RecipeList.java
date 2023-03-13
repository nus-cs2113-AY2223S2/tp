package seedu.meal360;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipeList extends ArrayList<Recipe> {

    public void addRecipe(Recipe recipe) {
        super.add(recipe);
    }

    public void editRecipe(String name, HashMap<String, Integer> ingredients) {

    }

    public void deleteRecipe(String name) {

    }

    public RecipeList listRecipes(String filter) {
        RecipeList filteredRecipeList = new RecipeList();
        if (filter == null) {
            return this;
        }
        for (Recipe recipe: this) {
            if (recipe.getName().contains(filter) || recipe.getIngredients().containsKey(filter)) {
                filteredRecipeList.addRecipe(recipe);
            }
        }
        return filteredRecipeList;
    }
}
