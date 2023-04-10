package seedu.mealcompanion.command.ingredients;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.ingredient.IngredientList;

/**
 * Represents the "ingredients list" command
 */
//@@author EthanYidong
public class IngredientsListCommand extends ExecutableCommand {
    @Override
    public void execute(MealCompanionSession mealCompanionSession) {
        IngredientList fridgeIngredients = mealCompanionSession.getIngredients();
        if (mealCompanionSession.getIngredients().isEmpty()) {
            mealCompanionSession.getUi().printMessage("You have no ingredients.");
        } else {
            mealCompanionSession.getUi().printMessage("You have the following ingredients:");
            mealCompanionSession.getUi().printMessage(fridgeIngredients.toString());
        }
    }
}
