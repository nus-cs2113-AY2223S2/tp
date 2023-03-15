package seedu.mealcompanion.command.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;

/**
 * Represents the "help" command.
 */
public class HelpCommand extends ExecutableCommand {
    public void execute(MealCompanionSession mealCompanionSession) {
        mealCompanionSession.getUi().printHelpMessages();
    }
}
