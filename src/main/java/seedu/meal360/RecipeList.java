package seedu.meal360;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipeList extends ArrayList<Recipe> {

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

    public RecipeList listRecipes(String[] filters) {
        RecipeList filteredRecipeList = new RecipeList();
        if (filters == null) {
            return this;
        }
        for (Recipe recipe : this) {
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
