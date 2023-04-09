package seedu.mealcompanion.command.factory.ingredients;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.ingredients.IngredientsSearchCommand;
import seedu.mealcompanion.exception.InvalidCommandException;
import seedu.mealcompanion.parser.CommandArguments;
import seedu.mealcompanion.parser.extractor.Extractor;
import seedu.mealcompanion.parser.extractor.PositionalArgumentExtractor;
import seedu.mealcompanion.parser.extractor.argtype.NotEmptyStringExtractor;

import java.util.List;

/**
 * Represents a factory for the "ingredients search" command
 */
//@@author EthanYidong
public class IngredientsSearchCommandFactory extends ExecutableCommandFactory {
    NotEmptyStringExtractor name = new NotEmptyStringExtractor();

    @Override
    public List<Extractor> getExtractors() {
        return List.of(new PositionalArgumentExtractor(name));
    }

    @Override
    public IngredientsSearchCommand buildCommand(MealCompanionSession mealCompanionSession,
                                                 CommandArguments arguments) throws InvalidCommandException {
        this.runExtractors(mealCompanionSession, arguments);
        return new IngredientsSearchCommand(name.getExtractedValue());
    }

    public String getCommandFormat() {
        return "ingredients search <optional_keyword>";
    }
}
