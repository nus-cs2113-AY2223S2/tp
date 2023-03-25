package seedu.mealcompanion.command.factory.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.misc.ClearCommand;
import seedu.mealcompanion.parser.CommandArguments;

/**
 * Represents a factory for the "clear" command.
 */
public class ClearCommandFactory extends ExecutableCommandFactory {
    public ClearCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments) {
        return new ClearCommand();
    }
}
