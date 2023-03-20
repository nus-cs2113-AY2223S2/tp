package seedu.mealcompanion.command.factory;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.parser.CommandArguments;
import seedu.mealcompanion.parser.CommandTokens;
import seedu.mealcompanion.router.Routable;

/**
 * Represents a factory which can build an <code>ExecutableCommand</code>
 */
public abstract class ExecutableCommandFactory implements Routable {
    public ExecutableCommandFactory resolve(CommandTokens commandTokens) {
        return this;
    }

    public abstract ExecutableCommand buildCommand(
            MealCompanionSession mealCompanionSession, CommandArguments arguments
    );
}
