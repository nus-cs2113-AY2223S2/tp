package seedu.mealcompanion.command.factory.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.misc.HelloWorldCommand;
import seedu.mealcompanion.parser.CommandArguments;

/**
 * Represents a factory for the easter egg "hello world" command.
 */
public class HelloWorldCommandFactory extends ExecutableCommandFactory {
    public HelloWorldCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments) {
        return new HelloWorldCommand(arguments.getPositionalArgument());
    }
}
