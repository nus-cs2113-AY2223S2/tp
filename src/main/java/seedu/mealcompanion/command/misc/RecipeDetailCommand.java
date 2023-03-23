package seedu.mealcompanion.command.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.ingredient.Ingredient;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.recipe.IngredientMetadata;
import seedu.mealcompanion.recipe.Instruction;
import seedu.mealcompanion.recipe.InstructionList;
import seedu.mealcompanion.recipe.Recipe;
import seedu.mealcompanion.recipe.RecipeList;

/**
 * Represents the "recipe detail" command.
 */
public class RecipeDetailCommand extends ExecutableCommand {

    String argument;
    public RecipeDetailCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Check if a string is a number
     * @param argument the string to check for
     * @return true if string is number, false otherwise
     */
    private boolean isIndex(String argument) {
        return argument.matches("[0-9]+");  //match a number with optional '-' and decimal.
    }

    /**
     * Find the index of a recipe in dukeSession list of recipes.
     * @param name the name of the recipe to look for
     * @param dukeSession the dukeSession containing the list of recipes to look in
     * @return index of recipe if recipe is found, else return -1
     */
    private int findIndex(String name, MealCompanionSession dukeSession) {
        RecipeList recipes = dukeSession.getRecipes();
        int index = 1;
        for (Recipe recipe : recipes.getRecipes()) {
            if (recipe.getName().equals(name)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public void execute(MealCompanionSession mealCompanionSession) {
        int index;
        if (!isIndex(this.argument)) {
            index = findIndex(this.argument, mealCompanionSession) - 1;
        } else {
            index = Integer.parseInt(this.argument) - 1;
        }

        Recipe recipe = mealCompanionSession.getRecipes().getRecipe(index);
        mealCompanionSession.getUi().printMessage("Recipe for " + recipe.getName());
        mealCompanionSession.getUi().printMessage(""); //print newline for all OS
        mealCompanionSession.getUi().printMessage("Calories: " + recipe.getCalorieCount());
        mealCompanionSession.getUi().printMessage(""); //print newline for all OS
        mealCompanionSession.getUi().printMessage("Prep Time: " + recipe.getPrepTime());
        mealCompanionSession.getUi().printMessage(""); //print newline for all OS
        mealCompanionSession.getUi().printMessage("Cook Time: " + recipe.getCookTime());
        mealCompanionSession.getUi().printMessage(""); //print newline for all OS
        mealCompanionSession.getUi().printMessage("Ingredients:");
        IngredientList ingredients = recipe.getIngredients();
        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient ingredient = ingredients.get(i);
            IngredientMetadata metadata = ingredient.getMetadata();
            String ingredientName = metadata.getName();
            String unitLabel = metadata.getUnitLabel();
            double qty = ingredient.getQuantity();
            mealCompanionSession.getUi().printMessage(Integer.toString(i+1) + ". " + ingredientName + " "
                    +Double.toString(qty) + " " + unitLabel);
        }
        mealCompanionSession.getUi().printMessage(""); //print newline for all OS
        mealCompanionSession.getUi().printMessage("Instructions:");
        InstructionList instructions = recipe.getInstructions();
        for (int i = 0; i < instructions.size(); i++) {
            Instruction instruction = instructions.get(i);
            mealCompanionSession.getUi().printMessage(Integer.toString(i+1) + ". " + instruction.getInstruction());
        }
    }
}

