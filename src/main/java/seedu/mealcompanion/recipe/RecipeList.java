package seedu.mealcompanion.recipe;

import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import seedu.mealcompanion.MealCompanionException;
import seedu.mealcompanion.serde.SerializableRecipe;

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
    
    public int size() {
        return recipes.size();
    }

    //@@author jingyaaa
    /**
     * Fetch a recipe by its specified name.
     * @param recipeName string containing recipe name to look for
     * @return recipe found
     */
    public Recipe getRecipe(String recipeName) throws MealCompanionException {
        for (Recipe recipe : recipes) {
            if (recipeName.equals(recipe.getName())) {
                return recipe;
            }
        }
        throw new MealCompanionException("Oops, recipe not found.");
    }
}
