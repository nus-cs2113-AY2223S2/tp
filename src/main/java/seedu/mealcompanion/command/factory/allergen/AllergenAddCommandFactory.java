package seedu.mealcompanion.command.factory.allergen;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.allergen.AllergenAddCommand;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.exception.InvalidCommandException;
import seedu.mealcompanion.parser.CommandArguments;
import seedu.mealcompanion.parser.extractor.Extractor;
import seedu.mealcompanion.parser.extractor.PositionalArgumentExtractor;
import seedu.mealcompanion.parser.extractor.argtype.IngredientNameExtractor;

import java.util.List;

//@@author EthanYidong
public class AllergenAddCommandFactory extends ExecutableCommandFactory {
    IngredientNameExtractor ingredientName = new IngredientNameExtractor();

    @Override
    public List<Extractor> getExtractors() {
        return List.of(new PositionalArgumentExtractor(ingredientName));
    }

    public AllergenAddCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments)
            throws InvalidCommandException {
        this.runExtractors(mealCompanionSession, arguments);
        return new AllergenAddCommand(ingredientName.getExtractedValue());
    }

    public String getCommandFormat() {
        return "allergen add <ingredient>";
    }
}
