package seedu.duke.command.factory.misc;

import seedu.duke.DukeSession;
import seedu.duke.command.ExecutableCommand;
import seedu.duke.command.factory.ExecutableCommandFactory;
import seedu.duke.command.misc.RecipeAllCommand;
import seedu.duke.parser.CommandArguments;

/**
 * Represents a factory for the "recipe all" command.
 */
public class RecipeAllCommandFactory extends ExecutableCommandFactory {
    @Override
    public ExecutableCommand buildCommand(DukeSession dukeSession, CommandArguments arguments) {
        return new RecipeAllCommand();
    }
}
