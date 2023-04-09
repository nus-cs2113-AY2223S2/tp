package seedu.mealcompanion.command.ingredients;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.recipe.IngredientDatabase;
import seedu.mealcompanion.recipe.IngredientMetadata;

//@@author EthanYidong
public class IngredientsSearchCommand extends ExecutableCommand {
    String searchTerm;

    public IngredientsSearchCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public void execute(MealCompanionSession mealCompanionSession) {
        IngredientDatabase db = IngredientDatabase.getDbInstance();
        for (IngredientMetadata ingredient : db.getKnownIngredients().values()) {
            if (this.searchTerm != null && !ingredient.getName().contains(this.searchTerm)) {
                continue;
            }
            mealCompanionSession.getUi().printMessage(ingredient.toString());
        }
    }
}
