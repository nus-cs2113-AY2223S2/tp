package seedu.mealcompanion.command.factory.allergen;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.allergen.AllergenRemoveCommand;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.exception.InvalidCommandException;
import seedu.mealcompanion.parser.CommandArguments;
import seedu.mealcompanion.parser.extractor.Extractor;
import seedu.mealcompanion.parser.extractor.PositionalArgumentExtractor;
import seedu.mealcompanion.parser.extractor.argtype.IngredientNameExtractor;

import java.util.List;

//@@author EthanYidong
public class AllergenRemoveCommandFactory extends ExecutableCommandFactory {
    IngredientNameExtractor ingredientName = new IngredientNameExtractor();

    @Override
    public List<Extractor> getExtractors() {
        return List.of(new PositionalArgumentExtractor(ingredientName));
    }

    public AllergenRemoveCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments)
            throws InvalidCommandException {
        this.runExtractors(mealCompanionSession, arguments);
        return new AllergenRemoveCommand(ingredientName.getExtractedValue());
    }
}
