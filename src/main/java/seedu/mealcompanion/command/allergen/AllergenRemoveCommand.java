package seedu.mealcompanion.command.allergen;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.recipe.IngredientMetadata;

/**
 * Represents the "add" command.
 */
//@@author EthanYidong
public class AllergenRemoveCommand extends ExecutableCommand {
    IngredientMetadata ingredient;

    public AllergenRemoveCommand(IngredientMetadata ingredient) {
        this.ingredient = ingredient;
    }

    public void execute(MealCompanionSession mealCompanionSession) {
        if (!mealCompanionSession.getAllergens().contains(ingredient)) {
            mealCompanionSession.getUi().printMessage("You are not allergic to: " + ingredient.getName());
            return;
        }
        mealCompanionSession.getAllergens().remove(ingredient);
        mealCompanionSession.getUi().printMessage("Removed allergy to: " + ingredient.getName());
    }
}
