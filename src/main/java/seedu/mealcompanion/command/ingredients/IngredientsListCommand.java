package seedu.mealcompanion.command.ingredients;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.ingredient.Ingredient;
import seedu.mealcompanion.ingredient.IngredientList;

/**
 * Represents the "ingredients list" command
 */
public class IngredientsListCommand extends ExecutableCommand {
    @Override
    public void execute(MealCompanionSession mealCompanionSession) {
        IngredientList fridgeIngredients = mealCompanionSession.getIngredients();

        int index = 0;

        mealCompanionSession.getUi().printMessage("You have the following ingredients:");

        for (Ingredient ingredient : fridgeIngredients.getIngredients()) {
            index += 1;
            mealCompanionSession.getUi().printMessage(index + ". " + ingredient.toString());
        }
    }
}
