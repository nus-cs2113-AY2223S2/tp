package seedu.mealcompanion.command.factory.ingredients;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.ingredients.IngredientsListCommand;
import seedu.mealcompanion.parser.CommandArguments;
import seedu.mealcompanion.parser.extractor.argtype.NotEmptyStringExtractor;

/**
 * Represents a factory for the "ingredients list" command
 */
//@@author EthanYidong
public class IngredientsListCommandFactory extends ExecutableCommandFactory {
    NotEmptyStringExtractor name = new NotEmptyStringExtractor();

    @Override
    public ExecutableCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments) {
        return new IngredientsListCommand();
    }

    public String getCommandFormat() {
        return "ingredients list";
    }
}
