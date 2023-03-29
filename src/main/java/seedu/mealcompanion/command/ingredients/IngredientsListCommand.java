package seedu.mealcompanion.command.ingredients;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.ingredient.IngredientList;

/**
 * Represents the "ingredients list" command
 */
public class IngredientsListCommand extends ExecutableCommand {
    @Override
    public void execute(MealCompanionSession mealCompanionSession) {
        IngredientList fridgeIngredients = mealCompanionSession.getIngredients();
        mealCompanionSession.getUi().printMessage("You have the following ingredients:");
        mealCompanionSession.getUi().printMessage(fridgeIngredients.toString());
    }
}
