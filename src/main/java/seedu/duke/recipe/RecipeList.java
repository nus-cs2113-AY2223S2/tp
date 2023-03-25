package seedu.duke.recipe;

import seedu.duke.exceptions.RecipeListEmptyError;
import seedu.duke.ui.StringLib;

import java.util.ArrayList;

public class RecipeList {
    protected static ArrayList<Recipe> recipeList;

    protected static int currRecipeNumber;

    public RecipeList() {
        recipeList = new ArrayList<>();
        currRecipeNumber = 0;
    }

    public RecipeList(ArrayList<Recipe> inputRecipeList) {
        recipeList = inputRecipeList;
        currRecipeNumber = inputRecipeList.size();
    }

    public static ArrayList<Recipe> getRecipeList() {
        return recipeList;
    }

    public static int getCurrRecipeNumber() {
        assert(currRecipeNumber == recipeList.size());
        return currRecipeNumber;
    }

    public static Recipe getRecipeFromList(int itemNum) {
        return recipeList.get(itemNum- 1);
    }

    public static Recipe getNewestRecipe() {
        return recipeList.get(currRecipeNumber-1);
    }

    public static void addNewRecipe(Recipe recipe) {
        recipeList.add(recipe);
        currRecipeNumber++;
        assert(currRecipeNumber == recipeList.size());
    }

    public static void removeRecipe(int index) throws RecipeListEmptyError {
        if (recipeList.isEmpty()) {
            throw new RecipeListEmptyError();
        }
        recipeList.remove(index-1);
        currRecipeNumber--;
        assert(currRecipeNumber == recipeList.size());
    }
    public static void clearRecipeList() {
        recipeList.clear();
        currRecipeNumber = 0;
        assert(recipeList.size() == 0);
    }
}
