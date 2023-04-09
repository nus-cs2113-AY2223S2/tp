package seedu.mealcompanion.command.factory.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.ingredients.ClearCommand;
import seedu.mealcompanion.parser.CommandArguments;

/**
 * Represents a factory for the "clear" command.
 */
//@@author TJW0911
public class ClearCommandFactory extends ExecutableCommandFactory {
    public ClearCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments) {
        return new ClearCommand();
    }

    public String getCommandFormat() {
        return "recipe favourite <index_number>";
    }
}
