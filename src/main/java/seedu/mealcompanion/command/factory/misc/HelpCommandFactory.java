package seedu.mealcompanion.command.factory.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.misc.HelpCommand;
import seedu.mealcompanion.parser.CommandArguments;

//@@author TJW0911

/**
 * Represents a factory for the "Help" command.
 */
public class HelpCommandFactory extends ExecutableCommandFactory {
    public HelpCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments) {
        return new HelpCommand();
    }

    public String getCommandFormat() {
        return "help";
    }
}
