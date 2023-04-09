package seedu.mealcompanion.command.factory;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.exception.InvalidCommandException;
import seedu.mealcompanion.parser.CommandArguments;
import seedu.mealcompanion.parser.CommandTokens;
import seedu.mealcompanion.parser.extractor.Extractor;
import seedu.mealcompanion.router.Routable;

import java.util.Collections;
import java.util.List;

/**
 * Represents a factory which can build an <code>ExecutableCommand</code>
 */
//@@author EthanYidong
public abstract class ExecutableCommandFactory implements Routable {
    public ExecutableCommandFactory resolve(CommandTokens commandTokens) {
        return this;
    }

    public List<Extractor> getExtractors() {
        return Collections.emptyList();
    }

    public void runExtractors(MealCompanionSession mealCompanionSession, CommandArguments arguments)
            throws InvalidCommandException {
        for (Extractor e : this.getExtractors()) {
            e.extractFrom(mealCompanionSession, arguments);
        }
    }

    public abstract ExecutableCommand buildCommand(
            MealCompanionSession mealCompanionSession, CommandArguments arguments
    ) throws InvalidCommandException;

    public abstract String getCommandFormat();
}
