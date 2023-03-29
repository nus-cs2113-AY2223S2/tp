package seedu.mealcompanion.command.factory.allergen;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.allergen.AllergenRemoveCommand;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.parser.CommandArguments;

public class AllergenRemoveCommandFactory extends ExecutableCommandFactory {
    public AllergenRemoveCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments) {
        return new AllergenRemoveCommand(arguments.getPositionalArgument());
    }
}
