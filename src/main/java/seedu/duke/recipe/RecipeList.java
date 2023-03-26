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

    public static void searchRecipeList(String term) {
        ArrayList<String> matches = new ArrayList<>();
        if (term.equals("")) {
            System.out.println(StringLib.MISSING_KEYWORD);
            return;
        }
        if (getCurrRecipeNumber() == 0) {
            System.out.println(StringLib.EMPTY_LIST_MESSAGE);
            return;
        }
        for (int i = 1; i <= getCurrRecipeNumber(); i++) {
            Recipe dish = getRecipeFromList(i);
            if (dish.getName().toLowerCase().contains(term.toLowerCase())) {
                matches.add(dish + " [Index: " + i + "]");
            }
        }
        if (matches.isEmpty()) {
            System.out.println(StringLib.NO_MATCHES);
        } else {
            System.out.println(StringLib.MATCHING_ITEMS);
            for (String match : matches) {
                System.out.println("  " + match);
            }
        }
    }
}
