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

    public RecipeList listRecipes(String[] filters) {
        RecipeList filteredRecipeList = this;
        if (filters == null) {
            return filteredRecipeList;
        }
        for (String filter : filters) {
            for (Recipe recipe: filteredRecipeList) {
                if (!recipe.getName().contains(filter) && !recipe.getIngredients().containsKey(filter)) {
                    filteredRecipeList.remove(recipe);
                }
            }
        }
        return filteredRecipeList;
    }
}
