package seedu.mealcompanion.command.factory.recipe;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.recipe.RecipeRandomCommand;
import seedu.mealcompanion.parser.CommandArguments;

//@@author ngyida

/**
 * Represents a factory for the "recipe random" command.
 */
public class RecipeRandomCommandFactory extends ExecutableCommandFactory {
    @Override
    public ExecutableCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments) {
        return new RecipeRandomCommand();
    }
}
