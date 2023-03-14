package seedu.duke.command.factory.ingredients;

import seedu.duke.DukeSession;
import seedu.duke.command.ExecutableCommand;
import seedu.duke.command.factory.ExecutableCommandFactory;
import seedu.duke.command.ingredients.IngredientsListCommand;
import seedu.duke.parser.CommandArguments;

/**
 * Represents a factory for the "ingredients list" command
 */
public class IngredientsListCommandFactory extends ExecutableCommandFactory {
    @Override
    public ExecutableCommand buildCommand(DukeSession dukeSession, CommandArguments arguments) {
        return new IngredientsListCommand();
    }
}
