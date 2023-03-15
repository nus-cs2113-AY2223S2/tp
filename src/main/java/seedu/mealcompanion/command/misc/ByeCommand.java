package seedu.mealcompanion.command.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;

/**
 * Represents the "bye" command.
 */
public class ByeCommand extends ExecutableCommand {
    public void execute(MealCompanionSession mealCompanionSession) {
        mealCompanionSession.getControlFlow().setQuit(true);
    }
}
