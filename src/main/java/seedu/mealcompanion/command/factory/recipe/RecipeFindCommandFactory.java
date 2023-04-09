package seedu.mealcompanion.command.factory.recipe;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.recipe.RecipeFindCommand;
import seedu.mealcompanion.exception.InvalidCommandException;
import seedu.mealcompanion.parser.CommandArguments;
import seedu.mealcompanion.parser.extractor.Extractor;
import seedu.mealcompanion.parser.extractor.PositionalArgumentExtractor;
import seedu.mealcompanion.parser.extractor.argtype.NotEmptyStringExtractor;

import java.util.List;

//@@author jingyaaa

/**
 * Represents a factory for the "recipe find" command.
 */
public class RecipeFindCommandFactory extends ExecutableCommandFactory {
    NotEmptyStringExtractor name = new NotEmptyStringExtractor();

    @Override
    public List<Extractor> getExtractors() {
        return List.of(new PositionalArgumentExtractor(name));
    }

    @Override
    public ExecutableCommand buildCommand(MealCompanionSession mealCompanionSession,
                                          CommandArguments arguments) throws InvalidCommandException {
        this.runExtractors(mealCompanionSession, arguments);
        return new RecipeFindCommand(name.getExtractedValue());
    }

    public String getCommandFormat() {
        return "recipe find <keyword>";
    }
}
