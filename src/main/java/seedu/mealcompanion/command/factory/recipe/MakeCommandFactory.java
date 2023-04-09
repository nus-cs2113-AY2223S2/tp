package seedu.mealcompanion.command.factory.recipe;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.recipe.MakeCommand;
import seedu.mealcompanion.exception.InvalidCommandException;
import seedu.mealcompanion.parser.CommandArguments;
import seedu.mealcompanion.parser.extractor.Extractor;
import seedu.mealcompanion.parser.extractor.PositionalArgumentExtractor;
import seedu.mealcompanion.parser.extractor.argtype.RecipeIndexExtractor;

import java.util.List;

//@@author TJW0911

/**
 * Represents a factory for the "make" command.
 */
public class MakeCommandFactory extends ExecutableCommandFactory {
    RecipeIndexExtractor recipe = new RecipeIndexExtractor();

    @Override
    public List<Extractor> getExtractors() {
        return List.of(new PositionalArgumentExtractor(recipe));
    }

    @Override
    public MakeCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments)
            throws InvalidCommandException {
        this.runExtractors(mealCompanionSession, arguments);
        return new MakeCommand(recipe.getExtractedValue());
    }
}
