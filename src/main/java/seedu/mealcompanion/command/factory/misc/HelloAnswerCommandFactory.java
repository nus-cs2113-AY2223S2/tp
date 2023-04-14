package seedu.mealcompanion.command.factory.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.misc.HelloAnswerCommand;
import seedu.mealcompanion.parser.CommandArguments;

/**
 * Represents a factory for the "hello psle" command of the easter egg function.
 */
//@@author Jjzeng123
public class HelloAnswerCommandFactory extends ExecutableCommandFactory {
    public HelloAnswerCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments) {
        return new HelloAnswerCommand();
    }

    public String getCommandFormat() {
        return "hello <answer>";
    }
}
