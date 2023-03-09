package seedu.meal360;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipeList extends ArrayList<Recipe> {
    public void addRecipe(Recipe recipe) {

    }

    public void editRecipe(String name, HashMap<String, Integer> ingredients) {

    }

    public Recipe deleteRecipe(int recipeNum) {
        Recipe recipeToDelete = super.get(recipeNum);
        super.remove(recipeToDelete);
        return recipeToDelete;
    }

    public void listRecipes() {

    }
}
