package seedu.mealcompanion.command.allergen;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.recipe.IngredientDatabase;
import seedu.mealcompanion.recipe.IngredientMetadata;

/**
 * Represents the "add" command.
 */
//@@author EthanYidong
public class AllergenRemoveCommand extends ExecutableCommand {
    String name;

    public AllergenRemoveCommand(String arguments) {
        this.name = arguments;
    }

    public void execute(MealCompanionSession mealCompanionSession) {
        try {
            IngredientDatabase db = IngredientDatabase.getDbInstance();
            IngredientMetadata newIngredient = db.getKnownIngredient(this.name);

            if (!mealCompanionSession.getAllergens().contains(newIngredient)) {
                mealCompanionSession.getUi().printMessage("You are not allergic to: " + this.name);
                return;
            }
            mealCompanionSession.getAllergens().remove(newIngredient);
            mealCompanionSession.getUi().printMessage("Removed allergy to: " + this.name);

        } catch (Exception e) {
            mealCompanionSession.getUi().printMessage(String.valueOf(e));
        }
    }
}
