package seedu.mealcompanion.command.factory.misc;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.recipe.RecipeUnfavouriteCommand;
import seedu.mealcompanion.parser.CommandArguments;

//@@author Jjzeng123
/**
 * Represents a factory for the "recipe unfavourite" command.
 */
public class RecipeUnfavouriteCommandFactory extends ExecutableCommandFactory {
    @Override
    public ExecutableCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments) {
        return new RecipeUnfavouriteCommand(arguments.getPositionalArgument());
    }
}