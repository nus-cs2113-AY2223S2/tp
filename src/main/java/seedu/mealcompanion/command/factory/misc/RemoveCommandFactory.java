package seedu.mealcompanion.command.factory.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.misc.RemoveCommand;
import seedu.mealcompanion.parser.CommandArguments;

//@@author TJW0911
/**
 * Represents a factory for the "remove" command.
 */
public class RemoveCommandFactory extends ExecutableCommandFactory {
    public RemoveCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments) {
        return new RemoveCommand(arguments.getPositionalArgument(), arguments.getFlagArgument("qty"));
    }
}
