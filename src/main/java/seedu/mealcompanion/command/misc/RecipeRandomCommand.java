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
import java.util.Random;

public class RecipeRandomCommand extends ExecutableCommand {

    private int getRandomInt() {
        Random rand = new Random();
        return rand.nextInt();
    }

    @Override
    public void execute(MealCompanionSession mealCompanionSession) {
        RecipeList recipes = mealCompanionSession.getRecipes();
        Recipe recipe = recipes.getRecipe(getRandomInt() % recipes.size());
        mealCompanionSession.getUi().printMessage("A random recipe is chosen:");
        mealCompanionSession.getUi().printMessage("Recipe for " + recipe.getName());
        mealCompanionSession.getUi().printMessage(""); //print newline for all OS
        mealCompanionSession.getUi().printMessage("Calories: " + recipe.getCalorieCount());
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
