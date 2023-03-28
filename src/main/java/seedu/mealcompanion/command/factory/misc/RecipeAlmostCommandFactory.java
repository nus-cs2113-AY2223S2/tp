package seedu.mealcompanion.command.factory.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.misc.RecipeAlmostCommand;
import seedu.mealcompanion.parser.CommandArguments;

/**
 * Represents a factory for the "recipe almost" command.
 */

public class RecipeAlmostCommandFactory extends ExecutableCommandFactory {
    public ExecutableCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments) {
        return new RecipeAlmostCommand();
    }
}
