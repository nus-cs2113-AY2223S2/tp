package seedu.mealcompanion.command.factory.recipe;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.recipe.RecipeDetailCommand;
import seedu.mealcompanion.exception.InvalidCommandException;
import seedu.mealcompanion.parser.CommandArguments;
import seedu.mealcompanion.parser.extractor.Extractor;
import seedu.mealcompanion.parser.extractor.PositionalArgumentExtractor;
import seedu.mealcompanion.parser.extractor.argtype.RecipeIndexExtractor;

import java.util.List;

//@@author ngyida

/**
 * Represents a factory for the "recipe detail" command.
 */
public class RecipeDetailCommandFactory extends ExecutableCommandFactory {
    RecipeIndexExtractor recipe = new RecipeIndexExtractor();

    @Override
    public List<Extractor> getExtractors() {
        return List.of(new PositionalArgumentExtractor(recipe));
    }

    @Override
    public ExecutableCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments)
            throws InvalidCommandException {
        this.runExtractors(mealCompanionSession, arguments);
        return new RecipeDetailCommand(recipe.getExtractedValue());
    }
}
