package seedu.meal360;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipeList extends ArrayList<Recipe> {
    private static final Ui ui = new Ui();

    public void addRecipe(Recipe recipe) {
        super.add(recipe);
    }

    public void editRecipe(String name, HashMap<String, Integer> ingredients) {

    }

    public void deleteRecipe(String name) {

    }

    public void listRecipes() {
    }
}
