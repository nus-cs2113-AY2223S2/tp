package seedu.meal360;

import seedu.meal360.exceptions.NoRecipeException;
import seedu.meal360.exceptions.RecipeNotFoundInTagException;
import seedu.meal360.exceptions.TagNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RecipeList extends ArrayList<Recipe> {

    private static IngredientList ingredientList = new IngredientList();
    public HashMap<String, HashMap<Recipe, Integer>> tags = new HashMap<>();

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

    public void addRecipeToTag(String tag, Recipe recipe) {
        boolean hasTag = tags.containsKey(tag);
        int tagDummy = 0;
        if (hasTag) {
            tags.get(tag).put(recipe, tagDummy);
        } else {
            assert !tags.containsKey(tag);
            HashMap<Recipe, Integer> tagRecipes = new HashMap<>();
            tagRecipes.put(recipe, tagDummy);
            tags.put(tag, tagRecipes);
            assert tags.size() > 0 : "tag's size is still 0.";
        }
    }

    public void removeRecipeFromTag(String tag, Recipe recipe) throws RecipeNotFoundInTagException {
        HashMap<Recipe, Integer> tagRecipeList = tags.get(tag);
        boolean isAbleToFindTheRecipe = tagRecipeList.containsKey(recipe);
        if (!isAbleToFindTheRecipe) {
            String errorMessage1 = "Unable to find the recipe: \"" + recipe.getName() + "\" in the" + " tag.";
            String errorMessage2 = "All the recipe before \"" + recipe.getName() + "\" (if any) are " +
                    "successfully removed from the tag.";
            throw new RecipeNotFoundInTagException(String.format("%-97s|\n| %-97s", errorMessage1, errorMessage2));
        }
        tagRecipeList.remove(recipe);
    }

    public RecipeList listRecipes(String[] filters, boolean isTag) throws TagNotFoundException, NoRecipeException {
        RecipeList filteredRecipeList = new RecipeList();
        boolean isNoFilter = filters == null;
        boolean isNotPassTheFilter;

        if (isTag) {
            filteredRecipeList = this.listTagRecipes(filters);
        } else if (!isTag && isNoFilter) {
            return this;
        } else if (!isTag) {
            for (Recipe recipe : this) {
                filteredRecipeList.add(recipe);
                for (String filter : filters) {
                    filter = filter.trim();
                    isNotPassTheFilter = !recipe.getName().contains(filter) &&
                            !recipe.getIngredients().containsKey(filter);
                    if (isNotPassTheFilter) {
                        filteredRecipeList.remove(recipe);
                    }
                }
            }
        }
        return filteredRecipeList;
    }

    public RecipeList listTagRecipes(String[] filters) throws TagNotFoundException, NoRecipeException {
        RecipeList filteredRecipeList = new RecipeList();
        HashMap<Recipe, Integer> tagRecipes;
        boolean hasNoRecipeInTheTag;
        boolean hasNoRecipeToReturn;
        boolean isNotFoundTag;

        // add all recipe from first tag to the list
        isNotFoundTag = !this.tags.containsKey(filters[0].trim());
        if (isNotFoundTag) {
            throw new TagNotFoundException("There is no \"" + filters[0] + "\" tag found. Please make sure you have " +
                            "entered the correct tag.");
        }
        this.tags.get(filters[0].trim()).forEach((recipe, dummy) -> filteredRecipeList.add(recipe));

        for (String filter : filters) {
            filter = filter.trim();
            tagRecipes = this.tags.get(filter);
            hasNoRecipeInTheTag = tagRecipes == null;
            hasNoRecipeToReturn = filteredRecipeList.size() == 0;

            if (hasNoRecipeInTheTag) {
                throw new NoRecipeException("There is nothing to list.");
            }
            if (hasNoRecipeToReturn) {
                return filteredRecipeList;
            }
            for (int index = filteredRecipeList.size() - 1; index >= 0; index--) {
                Recipe currentRecipe = filteredRecipeList.get(index);
                if (!tagRecipes.containsKey(currentRecipe)) {
                    filteredRecipeList.remove(currentRecipe);
                }
            }
        }
        return filteredRecipeList;
    }

    public RecipeList availableRecipes() {
        RecipeList availableRecipeList = new RecipeList();
        for (Recipe recipe : this) {
            if (recipe.isAvailable()) {
                availableRecipeList.add(recipe);
            }
        }
        return availableRecipeList;
    }

    public Recipe randomRecipe() {
        Random random = new Random();
        Recipe randomRecipe = this.get(random.nextInt(this.size()));
        return randomRecipe;
    }
}
