package seedu.mealcompanion.command.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;

//@@author TJW0911
/**
 * Represents the "Clear" command.
 */
public class ClearCommand extends ExecutableCommand {
    public void execute(MealCompanionSession mealCompanionSession) {
        mealCompanionSession.getIngredients().clear();
        mealCompanionSession.getUi().printMessage("All the ingredients in inventory has been cleared");
        mealCompanionSession.getIngredientStorage().writeIngredientsToFile(mealCompanionSession.getIngredients());
    }
}
