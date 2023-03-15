package seedu.mealcompanion.command.factory.ingredients;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.ingredients.IngredientsListCommand;
import seedu.mealcompanion.parser.CommandArguments;

/**
 * Represents a factory for the "ingredients list" command
 */
public class IngredientsListCommandFactory extends ExecutableCommandFactory {
    @Override
    public ExecutableCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments) {
        return new IngredientsListCommand();
    }
}
