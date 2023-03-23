package seedu.mealcompanion.command.factory.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.misc.MakeCommand;
import seedu.mealcompanion.parser.CommandArguments;

//@@author TJW0911
/**
 * Represents a factory for the "make" command.
 */
public class MakeCommandFactory extends ExecutableCommandFactory {
    public MakeCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments) {
        return new MakeCommand(arguments.getPositionalArgument());
    }
}
