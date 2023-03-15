package seedu.duke.command.factory.ingredients;

import seedu.duke.DukeSession;
import seedu.duke.command.ExecutableCommand;
import seedu.duke.command.factory.ExecutableCommandFactory;
import seedu.duke.command.ingredients.IngredientsSearchCommand;
import seedu.duke.parser.CommandArguments;

/**
 * Represents a factory for the "ingredients search" command
 */
public class IngredientsSearchCommandFactory extends ExecutableCommandFactory {
    @Override
    public ExecutableCommand buildCommand(DukeSession dukeSession, CommandArguments arguments) {
        return new IngredientsSearchCommand(arguments.getPositionalArgument());
    }
}
