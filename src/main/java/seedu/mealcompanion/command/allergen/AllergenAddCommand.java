package seedu.mealcompanion.command.allergen;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.recipe.IngredientMetadata;

/**
 * Represents the "add" command.
 */
public class AllergenAddCommand extends ExecutableCommand {
    IngredientMetadata ingredient;

    public AllergenAddCommand(IngredientMetadata ingredient) {
        this.ingredient = ingredient;
    }

    public void execute(MealCompanionSession mealCompanionSession) {

        if (mealCompanionSession.getAllergens().contains(ingredient)) {
            mealCompanionSession.getUi().printMessage("You are already allergic to: " + ingredient.getName());
            return;
        }
        mealCompanionSession.getAllergens().add(ingredient);
        mealCompanionSession.getUi().printMessage("Added allergy to: " + ingredient.getName());
    }
}
