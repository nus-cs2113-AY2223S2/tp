package seedu.mealcompanion.recipe;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import seedu.mealcompanion.MealCompanionException;
import seedu.mealcompanion.serde.SerializableRecipe;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//@@author ngyida
public class RecipeList {
    public ArrayList<Recipe> recipes;

    public RecipeList() {
        this.recipes = new ArrayList<>();
    }

    public RecipeList(String file) {
        this.recipes = new ArrayList<>();
        Gson gson = new Gson();
        InputStream in = this.getClass().getResourceAsStream(file);
        try (Reader reader = new InputStreamReader(in)) {
            // This is needed for GSON to return the expected instance of List<SerializableRecipe>
            TypeToken<?> typeToken= TypeToken.getParameterized(List.class, SerializableRecipe.class);
            Type recipeListType = typeToken.getType();
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
    
    //@@author jingyaaa
    /**
     * Fetch a recipe by its specified name.
     * @param recipeName string containing recipe name to look for
     * @return recipe found
     */
    public Recipe getRecipe(String recipeName) throws MealCompanionException {
        for (Recipe recipe : recipes) {
            if (recipeName.equalsIgnoreCase(recipe.getName())) {
                return recipe;
            }
        }
        throw new MealCompanionException("Oops, recipe not found.");
    }

    //@@author ngyida
    /**
     * Find the index (0-based) of a recipe by its specified name.
     * @param name the name of the recipe to look for
     * @return index of recipe if recipe is found, else return -1
     * @throws MealCompanionException if recipe name does not exist in the recipe list
     */
    public int findIndex(String name) throws MealCompanionException {
        int index = 0;
        for (Recipe recipe : this.recipes) {
            if (recipe.getName().equals(name)) {
                return index;
            }
            index++;
        }
        throw new MealCompanionException("Recipe not found!");
    }
    
    public int size() {
        return recipes.size();
    }

    public boolean isEmpty() {
        return recipes.isEmpty();
    }
}
