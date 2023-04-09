package seedu.mealcompanion.command.factory.ingredients;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.ingredients.RemoveCommand;
import seedu.mealcompanion.exception.InvalidCommandException;
import seedu.mealcompanion.parser.CommandArguments;
import seedu.mealcompanion.parser.extractor.Extractor;
import seedu.mealcompanion.parser.extractor.FlagArgumentExtractor;
import seedu.mealcompanion.parser.extractor.PositionalArgumentExtractor;
import seedu.mealcompanion.parser.extractor.argtype.IngredientNameExtractor;
import seedu.mealcompanion.parser.extractor.argtype.IntegerRangeExtractor;

import java.util.List;

//@@author TJW0911

/**
 * Represents a factory for the "remove" command.
 */
public class RemoveCommandFactory extends ExecutableCommandFactory {
    IngredientNameExtractor ingredientName = new IngredientNameExtractor();
    IntegerRangeExtractor removeAmount = new IntegerRangeExtractor(1, 10000);

    @Override
    public List<Extractor> getExtractors() {
        return List.of(
                new PositionalArgumentExtractor(ingredientName),
                new FlagArgumentExtractor("qty", removeAmount));
    }

    public RemoveCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments)
            throws InvalidCommandException {
        this.runExtractors(mealCompanionSession, arguments);
        return new RemoveCommand(ingredientName.getExtractedValue(), removeAmount.getExtractedValue());
    }
}
