package seedu.mealcompanion.command.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;

/**
 * Represents the Easter egg "hello world" command.
 */
//@@author Jjzeng123
public class HelloWorldCommand extends ExecutableCommand {
    String name;

    public HelloWorldCommand(String name) {
        this.name = name;
    }

    public void execute(MealCompanionSession mealCompanionSession) {
        if (this.name == null) {
            mealCompanionSession.getUi().printMessage("Hello, world!");
            mealCompanionSession.getUi().printMessage("Congratulations! You found our hidden easter egg!");
            mealCompanionSession.getUi().printMessage("Try the command <hello psle>");
            return;
        }
        mealCompanionSession.getUi().printMessage(String.format("Hello, %s!", this.name));
    }
}
