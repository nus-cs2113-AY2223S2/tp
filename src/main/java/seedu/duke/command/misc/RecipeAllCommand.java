package seedu.duke.command.misc;

import seedu.duke.DukeSession;
import seedu.duke.command.ExecutableCommand;
import seedu.duke.recipe.Recipe;
import seedu.duke.recipe.RecipeList;

/**
 * Represents the "recipe all" command.
 */
public class RecipeAllCommand extends ExecutableCommand {
    /**
     * List all recipes.
     *
     * @param dukeSession the DukeSession containing the list of recipes
     */
    @Override
    public void execute(DukeSession dukeSession) {
        RecipeList recipes = dukeSession.getRecipes();
        int index = 1;
        dukeSession.getUi().printMessage("Here is the full list of recipes:");
        for (Recipe recipe : recipes.getRecipes()) {
            dukeSession.getUi().printMessage(Integer.toString(index) + ". " + recipe.getName());
            index++;
        }
    }
}
