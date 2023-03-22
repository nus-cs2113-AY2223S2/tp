package seedu.mealcompanion.command.misc;

import seedu.mealcompanion.MealCompanionException;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.ingredient.Ingredient;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.recipe.IngredientMetadata;
import seedu.mealcompanion.recipe.Instruction;
import seedu.mealcompanion.recipe.InstructionList;
import seedu.mealcompanion.recipe.Recipe;
import seedu.mealcompanion.recipe.RecipeList;
import seedu.mealcompanion.ui.MealCompanionUI;

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
    private boolean isNum(String argument) {
        return argument.matches("[0-9]+");  //match a number with optional '-' and decimal.
    }

    /**
     * Find the index of a recipe in dukeSession list of recipes.
     * @param name the name of the recipe to look for
     * @param dukeSession the dukeSession containing the list of recipes to look in
     * @return index of recipe if recipe is found, else return -1
     */
    private int findIndex(String name, MealCompanionSession dukeSession) throws MealCompanionException {
        RecipeList recipes = dukeSession.getRecipes();
        int index = 1;
        for (Recipe recipe : recipes.getRecipes()) {
            if (recipe.getName().equals(name)) {
                return index;
            }
            index++;
        }
        throw new MealCompanionException("Recipe not found!");
    }

    private void printIngredients(MealCompanionUI ui, IngredientList ingredients) {
        ui.printMessage("Ingredients:");
        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient ingredient = ingredients.get(i);
            IngredientMetadata metadata = ingredient.getMetadata();
            String ingredientName = metadata.getName();
            String unitLabel = metadata.getUnitLabel();
            double qty = ingredient.getQuantity();
            ui.printMessage(Integer.toString(i+1) + ". " + ingredientName + " "
                    +Double.toString(qty) + " " + unitLabel);
        }
    }

    private void printInstructions(MealCompanionUI ui, InstructionList instructions) {
        ui.printMessage("Instructions:");
        for (int i = 0; i < instructions.size(); i++) {
            Instruction instruction = instructions.get(i);
            ui.printMessage(Integer.toString(i+1) + ". " + instruction.getInstruction());
        }
    }

    private void printRecipe(MealCompanionUI ui, Recipe recipe) {
        ui.printMessage("Recipe for " + recipe.getName());
        ui.printMessage(""); //print newline for all OS
        ui.printMessage("Calories: " + recipe.getCalorieCount());
        ui.printMessage(""); //print newline for all OS
        IngredientList ingredients = recipe.getIngredients();
        printIngredients(ui, ingredients);
        ui.printMessage(""); //print newline for all OS
        InstructionList instructions = recipe.getInstructions();
        printInstructions(ui, instructions);
    }

    @Override
    public void execute(MealCompanionSession mealCompanionSession) {
        try {
            if (this.argument == null) {
                throw new MealCompanionException("Missing argument!");
            }

            int index;
            if (!isNum(this.argument)) {
                index = findIndex(this.argument, mealCompanionSession) - 1;
            } else {
                index = Integer.parseInt(this.argument) - 1;
            }

            if (index < 0 || index >= mealCompanionSession.getRecipes().size()) {
                throw new MealCompanionException("Recipe index out of range!");
            }
            assert index >= 0 && index < mealCompanionSession.getRecipes().size();

            Recipe recipe = mealCompanionSession.getRecipes().getRecipe(index);
            printRecipe(mealCompanionSession.getUi(), recipe);
        } catch (MealCompanionException e) {
            mealCompanionSession.getUi().printMessage(String.valueOf(e));
        }
    }
}

