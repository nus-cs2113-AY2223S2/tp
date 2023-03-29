package seedu.mealcompanion.command.allergen;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.recipe.IngredientMetadata;

public class AllergenListCommand extends ExecutableCommand {
    @Override
    public void execute(MealCompanionSession mealCompanionSession) {
        mealCompanionSession.getUi().printMessage("You are allergic to:");
        for (IngredientMetadata allergen : mealCompanionSession.getAllergens()) {
            mealCompanionSession.getUi().printMessage(allergen.getName());
        }
    }
}
