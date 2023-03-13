package seedu.duke.command.factory.misc;

import seedu.duke.DukeSession;
import seedu.duke.command.ExecutableCommand;
import seedu.duke.command.factory.ExecutableCommandFactory;
import seedu.duke.command.misc.RecipeAllCommand;
import seedu.duke.command.misc.RecipePossibleCommand;
import seedu.duke.parser.CommandArguments;

public class RecipeAllCommandFactory extends ExecutableCommandFactory {
    @Override
    public ExecutableCommand buildCommand(DukeSession dukeSession, CommandArguments arguments) {
        return new RecipeAllCommand();
    }
}
