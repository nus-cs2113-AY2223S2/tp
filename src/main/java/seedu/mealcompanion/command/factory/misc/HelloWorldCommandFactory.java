package seedu.mealcompanion.command.factory.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.misc.HelloWorldCommand;
import seedu.mealcompanion.exception.InvalidCommandException;
import seedu.mealcompanion.parser.CommandArguments;
import seedu.mealcompanion.parser.extractor.Extractor;
import seedu.mealcompanion.parser.extractor.PositionalArgumentExtractor;
import seedu.mealcompanion.parser.extractor.argtype.StringExtractor;

import java.util.List;

/**
 * Represents a factory for the easter egg "hello world" command.
 */
//@@author EthanYidong
public class HelloWorldCommandFactory extends ExecutableCommandFactory {
    StringExtractor name = new StringExtractor();

    @Override
    public List<Extractor> getExtractors() {
        return List.of(new PositionalArgumentExtractor(name));
    }

    public HelloWorldCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments)
            throws InvalidCommandException {
        this.runExtractors(mealCompanionSession, arguments);
        return new HelloWorldCommand(name.getExtractedValue());
    }

    public String getCommandFormat() {
        return "hello world <optional_name>";
    }
}
