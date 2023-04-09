package seedu.mealcompanion.command.factory.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.misc.ByeCommand;
import seedu.mealcompanion.parser.CommandArguments;

/**
 * Represents a factory for the "bye" command.
 */
public class ByeCommandFactory extends ExecutableCommandFactory {
    public ByeCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments) {
        return new ByeCommand();
    }

    public String getCommandFormat() {
        return "bye";
    }
}
