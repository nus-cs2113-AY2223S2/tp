package seedu.mealcompanion.command.factory.allergen;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.allergen.AllergenAddCommand;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.parser.CommandArguments;

public class AllergenAddCommandFactory extends ExecutableCommandFactory {
    public AllergenAddCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments) {
        return new AllergenAddCommand(arguments.getPositionalArgument());
    }
}
