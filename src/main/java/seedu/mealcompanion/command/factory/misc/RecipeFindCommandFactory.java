package seedu.mealcompanion.command.factory.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.recipe.RecipeFindCommand;
import seedu.mealcompanion.parser.CommandArguments;

//@@author jingyaaa

/**
 * Represents a factory for the "recipe find" command.
 */
public class RecipeFindCommandFactory extends ExecutableCommandFactory {

    @Override
    public ExecutableCommand buildCommand(MealCompanionSession mealCompanionSession,
                                          CommandArguments arguments) {
        return new RecipeFindCommand(arguments.getPositionalArgument());
    }
}
