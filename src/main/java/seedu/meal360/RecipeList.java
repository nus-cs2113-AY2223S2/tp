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
        RecipeList filteredRecipeList = new RecipeList();
        if (filters == null) {
            return this;
        }
        for (Recipe recipe: this) {
            filteredRecipeList.add(recipe);
            for (String filter : filters) {
                filter = filter.trim();
                if (!recipe.getName().contains(filter) && !recipe.getIngredients().containsKey(filter)) {
                    filteredRecipeList.remove(recipe);
                }
            }
        }
        return filteredRecipeList;
    }
}
