package seedu.duke.command.misc;

import seedu.duke.DukeSession;
import seedu.duke.command.ExecutableCommand;
import seedu.duke.ingredient.Ingredient;
import seedu.duke.ingredient.IngredientList;
import seedu.duke.recipe.IngredientMetadata;
import seedu.duke.recipe.Instruction;
import seedu.duke.recipe.InstructionList;
import seedu.duke.recipe.Recipe;
import seedu.duke.recipe.RecipeList;

/**
 * Represents the "recipe {<name> || <recipe_index>}" command.
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
    private int findIndex(String name, DukeSession dukeSession) {
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
    public void execute(DukeSession dukeSession) {
        int index;
        if (!isIndex(this.argument)) {
            index = findIndex(this.argument, dukeSession) - 1;
        }
        else {
            index = Integer.parseInt(this.argument) - 1;
        }

        Recipe recipe = dukeSession.getRecipes().getRecipe(index);
        dukeSession.getUi().printMessage("Recipe for " + recipe.getName());
        dukeSession.getUi().printMessage(""); //print newline for all OS
        dukeSession.getUi().printMessage("Ingredients:");
        IngredientList ingredients = recipe.getIngredients();
        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient ingredient = ingredients.get(i);
            IngredientMetadata metadata = ingredient.getMetadata();
            String ingredientName = metadata.getName();
            String unitLabel = metadata.getUnitLabel();
            double qty = ingredient.getQuantity();
            dukeSession.getUi().printMessage(Integer.toString(i+1) + ". " + ingredientName + " " +Double.toString(qty) +
                    " " + unitLabel);
        }
        dukeSession.getUi().printMessage(""); //print newline for all OS
        dukeSession.getUi().printMessage("Instructions:");
        InstructionList instructions = recipe.getInstructions();
        for (int i = 0; i < instructions.size(); i++) {
            Instruction instruction = instructions.get(i);
            dukeSession.getUi().printMessage(Integer.toString(i+1) + ". " + instruction.getInstruction());
        }
    }
}

