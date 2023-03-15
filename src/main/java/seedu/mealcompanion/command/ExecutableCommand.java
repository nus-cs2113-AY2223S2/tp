package seedu.mealcompanion.command;

import seedu.mealcompanion.MealCompanionSession;

/**
 * Represents a fully parsed command which can be executed.
 */
public abstract class ExecutableCommand {
    public abstract void execute(MealCompanionSession mealCompanionSession);
}
