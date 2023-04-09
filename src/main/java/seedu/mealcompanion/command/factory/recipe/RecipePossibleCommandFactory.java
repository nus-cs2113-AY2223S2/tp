package seedu.mealcompanion.command.factory.recipe;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.recipe.RecipePossibleCommand;
import seedu.mealcompanion.parser.CommandArguments;

//@@author ngyida

/**
 * Represents a factory for the "recipe possible" command.
 */
public class RecipePossibleCommandFactory extends ExecutableCommandFactory {
    @Override
    public ExecutableCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments) {
        return new RecipePossibleCommand();
    }
}
