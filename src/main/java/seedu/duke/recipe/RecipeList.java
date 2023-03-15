package seedu.duke.recipe;

import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import seedu.duke.serde.SerializableRecipe;

public class RecipeList {
    public ArrayList<Recipe> recipes;

    public RecipeList() {
        this.recipes = new ArrayList<>();
        Gson gson = new Gson();
        try (Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/recipes.json"))) {
            // This is needed for GSON to return the expected instance of List<SerializableRecipe>
            Type recipeListType =
                    TypeToken.getParameterized(List.class, SerializableRecipe.class).getType();
            List<SerializableRecipe> recipeList = gson.fromJson(reader, recipeListType);

            for (SerializableRecipe recipe : recipeList) {
                this.recipes.add(recipe.toRecipe());
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid ingredients list JSON resource." +
                    "This should be caught by automatic testing");
        }
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void add(Recipe recipe) {
        recipes.add(recipe);
    }

    public Recipe getRecipe(int index) {
        return recipes.get(index);
    }
}
