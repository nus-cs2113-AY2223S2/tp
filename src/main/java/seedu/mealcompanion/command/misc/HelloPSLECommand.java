package seedu.mealcompanion.command.misc;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;

//@@author Jjzeng123
public class HelloPSLECommand extends ExecutableCommand {

    public void execute(MealCompanionSession mealCompanionSession) {
        mealCompanionSession.getUi().printMessage("Congratulations! You have unlocked the secret puzzle!");
        mealCompanionSession.getUi().printMessage("To unlock the hidden secret, solve this puzzle!");
        mealCompanionSession.getUi().printMessage("Jamie and John used $61.20 each to buy some egg tarts. "
                + "Jamie has a 15% discount coupon and bought 6 more egg tarts than John.");
        mealCompanionSession.getUi().printMessage("a) How many egg tarts did Jamie buy?");
        mealCompanionSession.getUi().printMessage("b) What is the cost of an egg tart (in dollars)?");
        mealCompanionSession.getUi().printMessage("Enter your answer in the following format (ensure there is no space)"
                + ": hello <answer for a>$<answer for b>");
    }
}
