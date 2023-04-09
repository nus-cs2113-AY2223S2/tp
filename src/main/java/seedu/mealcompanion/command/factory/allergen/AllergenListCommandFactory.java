package seedu.mealcompanion.command.factory.allergen;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.allergen.AllergenListCommand;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.parser.CommandArguments;

//@@author EthanYidong
public class AllergenListCommandFactory extends ExecutableCommandFactory {
    public AllergenListCommand buildCommand(MealCompanionSession mealCompanionSession, CommandArguments arguments) {
        return new AllergenListCommand();
    }

    public String getCommandFormat() {
        return "allergen list";
    }
}
