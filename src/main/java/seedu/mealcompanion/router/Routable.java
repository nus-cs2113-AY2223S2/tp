package seedu.mealcompanion.router;

import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.parser.CommandTokens;

/**
 * Represents classes which can resolve a set of <code>CommandTokens</code> to a <code>ExecutableCommandFactory</code>.
 */
public interface Routable {
    /**
     * Returns the resolved <code>ExecutableCommandFactory</code> by the <code>Routable</code> object.
     *
     * @param commandTokens the command tokens to resolve.
     * @return the resolved command factory.
     */
    ExecutableCommandFactory resolve(CommandTokens commandTokens);
}
