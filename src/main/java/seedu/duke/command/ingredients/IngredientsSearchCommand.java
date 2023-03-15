package seedu.duke.command.ingredients;

import seedu.duke.DukeSession;
import seedu.duke.command.ExecutableCommand;
import seedu.duke.recipe.IngredientDatabase;
import seedu.duke.recipe.IngredientMetadata;

public class IngredientsSearchCommand extends ExecutableCommand {
    String searchTerm;

    public IngredientsSearchCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public void execute(DukeSession dukeSession) {
        IngredientDatabase db = IngredientDatabase.getDbInstance();
        for(IngredientMetadata ingredient : db.getKnownIngredients().values()) {
            if (this.searchTerm != null && !ingredient.getName().contains(this.searchTerm)) {
                continue;
            }
            dukeSession.getUi().printMessage(ingredient.toString());
        }
    }
}
