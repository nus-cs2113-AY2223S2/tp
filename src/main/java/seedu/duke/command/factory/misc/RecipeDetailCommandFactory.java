package seedu.duke.command.factory.misc;

import seedu.duke.DukeSession;
import seedu.duke.command.ExecutableCommand;
import seedu.duke.command.factory.ExecutableCommandFactory;
import seedu.duke.command.misc.RecipeAllCommand;
import seedu.duke.command.misc.RecipeDetailCommand;
import seedu.duke.parser.CommandArguments;

/**
 * Represents a factory for the "recipe {<name> || <recipe_index>}" command.
 */
public class RecipeDetailCommandFactory extends ExecutableCommandFactory {
    @Override
    public ExecutableCommand buildCommand(DukeSession dukeSession, CommandArguments arguments) {
        return new RecipeDetailCommand(arguments.getPositionalArgument());
    }
}