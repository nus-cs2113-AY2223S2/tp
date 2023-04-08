package seedu.mealcompanion.command.ingredients;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.ingredient.IngredientList;

//@@author TJW0911
/**
 * Represents the "Clear" command.
 */
public class ClearCommand extends ExecutableCommand {
    public void execute(MealCompanionSession mealCompanionSession) {
        IngredientList ingredients = mealCompanionSession.getIngredients();
        ingredients.clear();
        mealCompanionSession.getUi().printMessage("All the ingredients in inventory has been cleared");
        mealCompanionSession.getIngredientStorage().writeIngredientsToFile(ingredients);
    }
}
