package seedu.mealcompanion.command.allergen;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.recipe.IngredientDatabase;
import seedu.mealcompanion.recipe.IngredientMetadata;

/**
 * Represents the "add" command.
 */
public class AllergenAddCommand extends ExecutableCommand {
    String name;

    public AllergenAddCommand(String arguments) {
        this.name = arguments;
    }

    public void execute(MealCompanionSession mealCompanionSession) {
        try {
            IngredientDatabase db = IngredientDatabase.getDbInstance();
            IngredientMetadata newIngredient = db.getKnownIngredient(this.name);

            if (mealCompanionSession.getAllergens().contains(newIngredient)) {
                mealCompanionSession.getUi().printMessage("You are already allergic to: " + this.name);
                return;
            }
            mealCompanionSession.getAllergens().add(newIngredient);
            mealCompanionSession.getUi().printMessage("Added allergy to: " + this.name);

        } catch (Exception e) {
            mealCompanionSession.getUi().printMessage(String.valueOf(e));
        }
    }
}
