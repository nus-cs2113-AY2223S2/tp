package seedu.duke.recipe;

import seedu.duke.exceptions.RecipeListEmptyException;
import seedu.duke.ui.StringLib;

import java.util.ArrayList;

import static seedu.duke.ui.StringLib.MISSING_KEYWORD;
import static seedu.duke.ui.StringLib.EMPTY_LIST_MESSAGE;
import static seedu.duke.ui.StringLib.NO_MATCHES;
import static seedu.duke.ui.StringLib.MATCHING_ITEMS;

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

    public static void removeRecipe(int index) throws RecipeListEmptyException {
        if (recipeList.isEmpty()) {
            throw new RecipeListEmptyException();
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
        term = term.trim().toLowerCase();
        if (term.equals(StringLib.EMPTY_STRING)) {
            System.out.println(MISSING_KEYWORD);
            return;
        }
        if (getCurrRecipeNumber() == 0) {
            System.out.println(EMPTY_LIST_MESSAGE);
            return;
        }
        for (int i = 1; i <= getCurrRecipeNumber(); i++) {
            Recipe dish = getRecipeFromList(i);
            if (dish.getName().trim().toLowerCase().contains(term)) {
                matches.add(dish + " [Index: " + i + "]");
            }
        }
        if (matches.isEmpty()) {
            System.out.println(NO_MATCHES);
        } else {
            System.out.println(MATCHING_ITEMS);
            for (String match : matches) {
                System.out.println("  " + match);
            }
        }
    }

    public static void searchByTag(String tag) {
        ArrayList<String> matches = new ArrayList<>();
        tag = tag.trim().toLowerCase();
        if (tag.equals(StringLib.EMPTY_STRING)) {
            System.out.println(MISSING_KEYWORD);
        } else if (getCurrRecipeNumber() == 0) {
            System.out.println(EMPTY_LIST_MESSAGE);
        } else {
            for (int i = 1; i <= getCurrRecipeNumber(); i++) {
                Recipe dish = getRecipeFromList(i);
                if (dish.getTag().trim().toLowerCase().contains(tag)) {
                    matches.add(dish + " [Index: " + i + "]");
                }
            }
            if (matches.isEmpty()) {
                System.out.println(NO_MATCHES);
            } else {
                System.out.println(MATCHING_ITEMS);
                for (String match : matches) {
                    System.out.println("  " + match);
                }
            }
        }
    }
}
