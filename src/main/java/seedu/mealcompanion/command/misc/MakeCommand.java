package seedu.mealcompanion.command.misc;

import seedu.mealcompanion.MealCompanionException;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.ingredient.Ingredient;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.recipe.Recipe;
import seedu.mealcompanion.recipe.RecipeList;

//@@author TJW0911
/**
 * Represents the "make" command.
 */
public class MakeCommand extends ExecutableCommand {

    String name;

    public MakeCommand(String argument) {
        this.name = argument;
    }

    /**
     * Finds the index of a specified recipe name in the recipes list
     *
     * @param recipes the list of recipes
     * @return the index of the recipe in the recipes list and -1 if recipe name does not exist
     */
    private int findRecipeName(RecipeList recipes) {
        for (int i = 0; i < recipes.recipes.size(); i += 1) {
            if (recipes.getRecipe(i).getName().equalsIgnoreCase(this.name)) {
                return i;
            }
        }
        return -1;
    }

    //@@author ngyida
    /**
     * Check if an ingredientList has a sufficient amount of an ingredient.
     *
     * @param recipeIngredient  the ingredient to look for
     * @param fridgeIngredients the list of ingredients to check in
     * @return true if the list of ingredients have sufficient quantity of that ingredient, false otherwise
     */
    private boolean hasEnoughIngredient(Ingredient recipeIngredient, IngredientList fridgeIngredients) {
        String recipeIngredientName = recipeIngredient.getMetadata().getName();
        double recipeIngredientQty = recipeIngredient.getQuantity();
        for (Ingredient fridgeIngredient : fridgeIngredients.getIngredients()) {
            if (fridgeIngredient.getMetadata().getName().equals(recipeIngredientName)
                    && fridgeIngredient.getQuantity() >= recipeIngredientQty) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if a recipe can be made using a list of ingredients given.
     *
     * @param recipe            the recipe to be made
     * @param fridgeIngredients the list of ingredients used to make the recipe
     * @return true if the recipe can be made using the list of ingredients, false otherwise
     */
    private boolean canMakeRecipe(Recipe recipe, IngredientList fridgeIngredients) {
        IngredientList recipeIngredients = recipe.getIngredients();
        for (Ingredient recipeIngredient : recipeIngredients.getIngredients()) {
            if (!hasEnoughIngredient(recipeIngredient, fridgeIngredients)) {
                return false;
            }
        }
        return true;
    }

    //@@author TJW0911

    /**
     * Removes a specified quantity of an ingredient from the ingredient list
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of ingredients
     * @param quantity the quantity of ingredient to be removed
     * @param name the name of the ingredient to be removed
     */

    private void removeIngredient(MealCompanionSession mealCompanionSession, Double quantity, String name) {
        int indexOfExistingIngredient = mealCompanionSession.getIngredients().findIndex(name);
        double fridgeQuantity = mealCompanionSession.getIngredients().get(indexOfExistingIngredient).getQuantity();
        assert fridgeQuantity >= quantity: "fridgeQuantity should be more than quantity to be removed";
        double newQuantity = fridgeQuantity - quantity;
        mealCompanionSession.getIngredients().get(indexOfExistingIngredient).setQuantity(newQuantity);
        if (newQuantity == 0) {
            mealCompanionSession.getIngredients().remove(indexOfExistingIngredient);
            mealCompanionSession.getUi().printMessage(String.format("All %s has been removed", name));
        }
        mealCompanionSession.getIngredientStorage().writeIngredientsToFile(mealCompanionSession.getIngredients());
    }

    /**
     * Removes the ingredients needed to make the specified recipe from inventory
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of ingredients
     * @param recipe the recipe that is to be made
     */
    private void makeRecipe(MealCompanionSession mealCompanionSession, Recipe recipe) {
        IngredientList recipeIngredients = recipe.getIngredients();
        for (int i = 0; i < recipeIngredients.size(); i += 1) {
            double quantityToRemove = recipeIngredients.get(i).getQuantity();
            String name = recipeIngredients.get(i).getMetadata().getName();
            removeIngredient(mealCompanionSession, quantityToRemove, name);
        }
        mealCompanionSession.getUi().printMessage("Success! The ingredients needed were removed from inventory");
    }

    /**
     * Executes the "make" command
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of ingredients and recipes
     */

    public void execute(MealCompanionSession mealCompanionSession) {
        try {
            RecipeList recipes = mealCompanionSession.getRecipes();
            if(name == null || name.trim().isEmpty()) {
                throw new MealCompanionException("recipe name cannot be blank");
            }
            int index = findRecipeName(recipes);
            if (index == -1) {
                throw new MealCompanionException("recipe name not found");
            }
            Recipe recipe = recipes.getRecipe(index);
            IngredientList fridgeIngredients = mealCompanionSession.getIngredients();
            if (canMakeRecipe(recipe, fridgeIngredients)) {
                makeRecipe(mealCompanionSession, recipe);
            } else {
                throw new MealCompanionException("Ingredients in inventory is insufficient");
            }
        } catch (Exception e) {
            mealCompanionSession.getUi().printMessage(String.valueOf(e));
        }
    }
}
