package seedu.mealcompanion.command.factory.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.misc.RecipeDetailCommand;
import seedu.mealcompanion.parser.CommandArguments;

/**
 * Represents a factory for the "recipe detail" command.
 */
public class RecipeDetailCommandFactory extends ExecutableCommandFactory {
    @Override
    public ExecutableCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments) {
        return new RecipeDetailCommand(arguments.getPositionalArgument());
    }
}
