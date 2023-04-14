package seedu.mealcompanion.recipe;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import seedu.mealcompanion.exception.MealCompanionException;
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

    /**
     * Constructor for RecipeList class with no recipe initialized.
     */
    public RecipeList() {
        this.recipes = new ArrayList<>();
    }

    /**
     * Constructor for RecipeList class with recipes initialized from saved file.
     *
     * @param file file containing recipes
     */
    public RecipeList(String file) {
        this.recipes = new ArrayList<>();
        Gson gson = new Gson();
        InputStream in = this.getClass().getResourceAsStream(file);
        try (Reader reader = new InputStreamReader(in)) {
            // This is needed for GSON to return the expected instance of List<SerializableRecipe>
            TypeToken<?> typeToken = TypeToken.getParameterized(List.class, SerializableRecipe.class);
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

    /**
     * Get ArrayList of Recipes
     *
     * @return ArrayList of Recipes
     */
    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    /**
     * Add a recipe into RecipeList.
     *
     * @param recipe the recipe to be added
     */
    public void add(Recipe recipe) {
        recipes.add(recipe);
    }

    /**
     * Get a recipe from RecipeList using an index.
     *
     * @param index the index of the recipe to be retrieved
     * @return the recipe specified by the index
     */
    public Recipe getRecipe(int index) {
        return recipes.get(index);
    }

    //@@author jingyaaa

    /**
     * Fetch a recipe by its specified name.
     *
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
     * Find the index (0-based) of a recipe by its specified name. Name search is non-case sensitive.
     *
     * @param name the name of the recipe to look for
     * @return index of recipe if recipe is found
     * @throws MealCompanionException if recipe name does not exist in the recipe list
     */
    public int findIndex(String name) throws MealCompanionException {
        String nameInLowerCase = name.toLowerCase();
        int index = 0;
        for (Recipe recipe : this.recipes) {
            String recipeNameInLowerCase = recipe.getName().toLowerCase();
            if (recipeNameInLowerCase.equals(nameInLowerCase)) {
                return index;
            }
            index++;
        }
        throw new MealCompanionException("Recipe not found!");
    }

    /**
     * Get the number of recipes in RecipeList.
     *
     * @return the number of recipes
     */
    public int size() {
        return recipes.size();
    }

    /**
     * Check if RecipeList contains any recipe.
     *
     * @return True if RecipeList does not contain any recipe. Else, false.
     */
    public boolean isEmpty() {
        return recipes.isEmpty();
    }

    /**
     * Get a formatted string of the list of recipe names.
     *
     * @return a formatted list of the recipe names
     */
    public String getRecipeNameList() {
        int index = 1;
        StringBuilder recipeNameList = new StringBuilder();
        for (Recipe recipe : this.getRecipes()) {
            recipeNameList.append(recipe.getFavStatus() + Integer.toString(index) + ". "
                    + recipe.getName() + System.lineSeparator());
            index++;
        }
        return String.valueOf(recipeNameList);
    }
}
