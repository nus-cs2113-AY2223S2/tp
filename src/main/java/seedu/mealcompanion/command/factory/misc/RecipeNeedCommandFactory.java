package seedu.mealcompanion.command.factory.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.misc.RecipeNeedCommand;
import seedu.mealcompanion.parser.CommandArguments;


//@@author jingyaaa
/**
 * Represents a factory for the "recipe need" command.
 */
public class RecipeNeedCommandFactory extends ExecutableCommandFactory {

    @Override
    public ExecutableCommand buildCommand(MealCompanionSession mealCompanionSession,
                                          CommandArguments arguments) {
        return new RecipeNeedCommand(arguments.getPositionalArgument());
    }
}
