package seedu.mealcompanion.command.factory.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.misc.HelloPSLECommand;
import seedu.mealcompanion.parser.CommandArguments;

/**
 * Represents a factory for the "hello psle" command of the easter egg function.
 */
//@@author Jjzeng123
public class HelloPSLECommandFactory extends ExecutableCommandFactory {
    public HelloPSLECommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments) {
        return new HelloPSLECommand();
    }

    public String getCommandFormat() {
        return "hello psle";
    }
}
