package seedu.duke.command.ingredients;

import seedu.duke.DukeSession;
import seedu.duke.command.ExecutableCommand;
import seedu.duke.ingredient.Ingredient;
import seedu.duke.ingredient.IngredientList;

/**
 * Represents the "ingredients list" command
 */
public class IngredientsListCommand extends ExecutableCommand {
    @Override
    public void execute(DukeSession dukeSession) {
        IngredientList fridgeIngredients = dukeSession.getIngredients();

        int index = 0;

        dukeSession.getUi().printMessage("You have the following ingredients:");

        for (Ingredient ingredient : fridgeIngredients.getIngredients()) {
            index += 1;
            dukeSession.getUi().printMessage(index + ". " + ingredient.toString());
        }
    }
}
