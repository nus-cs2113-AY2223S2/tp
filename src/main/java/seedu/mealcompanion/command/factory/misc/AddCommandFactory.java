package seedu.mealcompanion.command.factory.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.misc.AddCommand;
import seedu.mealcompanion.parser.CommandArguments;

//@@author TJW0911
/**
 * Represents a factory for the "add" command.
 */
public class AddCommandFactory extends ExecutableCommandFactory {
    public AddCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments) {
        return new AddCommand(arguments.getPositionalArgument(), arguments.getFlagArgument("qty"));
    }
}
